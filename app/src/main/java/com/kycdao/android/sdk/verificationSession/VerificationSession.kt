package com.kycdao.android.sdk.verificationSession

import android.net.Uri
import android.webkit.URLUtil
import androidx.activity.ComponentActivity
import com.bitraptors.networking.api.models.NetworkErrorResponse
import com.kycdao.android.sdk.CustomKoinComponent
import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.dto.UserDto
import com.kycdao.android.sdk.exceptions.*
import com.kycdao.android.sdk.model.*
import com.kycdao.android.sdk.model.functions.mint.MintFunction
import com.kycdao.android.sdk.model.functions.mint.MintingProperties
import com.kycdao.android.sdk.model.functions.mint.MintingTransactionResult
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.request_models.AuthorizeMintingRequestBody
import com.kycdao.android.sdk.network.request_models.LoginRequestBody
import com.kycdao.android.sdk.network.request_models.MintTokenBody
import com.kycdao.android.sdk.network.request_models.UpdateUserRequestBody
import com.kycdao.android.sdk.usecase.IdentityVerificationUseCase
import com.kycdao.android.sdk.usecase.IdentityVerificationUseCaseImp

import com.kycdao.android.sdk.util.asHexString
import com.kycdao.android.sdk.util.convertBigInteger
import com.kycdao.android.sdk.util.seconds
import com.kycdao.android.sdk.wallet.WalletSession
import com.withpersona.sdk2.inquiry.InquiryResponse
import kotlinx.coroutines.*
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt
import timber.log.Timber
import java.math.BigInteger
import java.util.*
import kotlin.coroutines.suspendCoroutine

/***
 * A verification session object which contains session related data and session related operations
 *
 * Instances should be created by calling the VerificationManager-s createSession function.
 */
data class VerificationSession internal constructor(
	/***
	 * 	Wallet address used to create the session
	 */
	val walletAddress: String,
	private val network: Network,
	private val kycConfig: SmartContractConfig?,
	private val accreditedConfig: SmartContractConfig?,
	private val personaData: Persona,
	private val sessionData: SessionData,
	internal val rpcURL : String,
	/***
	 * The wallet session associated with this verification session, used to communicate with a wallet
	 */
	val walletSession: WalletSession,
	private var authorizeMintingResponse: AuthorizeMintingResponse? = null,
) : CustomKoinComponent() {

	internal companion object : CustomKoinComponent() {
		private val IDENTITY_VERIFICATION_POLL_INTERVAL = 10.seconds
		private val EMAIL_CONFIRMED_VERIFICATION_POLL_INTERVAL = 10.seconds
	}

	private val web3j: Web3j by inject() {
		parametersOf(rpcURL)
	}
	private val networkDatasource : NetworkDatasource by inject()
	private var scope = CoroutineScope(Dispatchers.IO)

	/**
	 * A unique identifier for this session
	 */
	val id: String = UUID.randomUUID().toString()

	/**
	 * The ID of the chain used specified in <a href="https://github.com/ChainAgnostic/CAIPs/blob/master/CAIPs/caip-2.md">CAIP-2 format</a>
	 */
	val chainId: String
		get() = network.caip2id

	/**
	 * Represents whether the disclaimer has been accepted or not.
	 */
	val disclaimerAccepted: Boolean
		get() = sessionData.user.disclaimerAccepted?.isNotEmpty() ?: false

	/**
	 * Represents whether the email address associated with the session has been confirmed or not.
	 */
	val emailConfirmed: Boolean
		get() = sessionData.user.isEmailConfirmed()

	/**
	 * Whether the user is logged in or not.
	 */
	val loggedIn: Boolean
		get() = sessionData.user.id != null
	private val residencyProvided: Boolean
		get() = sessionData.user.residency?.isNotEmpty() ?: false
	private val emailProvided: Boolean
		get() = sessionData.user.email?.isNotEmpty() ?: false

	/**
	 * A value representing whether all the necessary information has been provided or not.
	 * The necessary informations include the following:
	 * Both residency information and email address has been provided and we know if the user is a legal entity or not.
	 */
	val requiredInformationProvided: Boolean
		get() = residencyProvided && emailProvided && sessionData.user.isLegalEntity != null

	/**
	 * The verification status of the user
	 */
	val verificationStatus: VerificationStatus
		get() = sessionData.user.verificationStatus()

	private var emailPollJob: Job? = null
	private var verificationPollJob: Job? = null
	/*fun getState(): State {
		return when {
			!walletConnected() -> State.CONNECT_WALLET
			!hasSession() -> State.SESSION_REQUIRED
			!isLogged() -> State.LOGIN_REQUIRED
			!hasUserInfo() -> State.USER_INFORMATION_REQUIRED
			!isEmailConfirmed() -> State.WAIT_EMAIL_CONFIRMED
			!identityVerificationCompleted() -> State.IDENTITY_VERIFICATION
			!isIdentityVerified() -> State.POLL_IDENTITY_VERIFICATION_RESULT
			!nftSelected() -> State.NFT_IMAGE_SELECTION
			!authorizeMintingFinished() -> State.WAITING_FOR_MINTING_AUTHORISATION
			!feeCalculationFinished() -> State.CALCULATE_FEE
			!mintStarted() -> State.MINTING
			!mintFinished() -> State.CHECK_MINTING
			!mintTokenSent() -> State.POST_MINT_TOKEN_ID
			else -> State.COMPLETED
		}
	}*/

	private fun loginProof(): String {
		return "kycDAO-login-${sessionData.nonce}"
	}

	/**
	 * Provides the user selectable NFT images
	 * @return A list of image related data
	 */
	fun getNFTImages(): List<TokenImage> {
		return sessionData.user.availableImages.filter { it.imageType == ImageType.Identicon }
	}


	/**
	 * Logs in the user to the current session
	 * The user will be redirected to their wallet, where they will have to sign a session data in order to login
	 */
	suspend fun login() {
		val signature: String?
		try {
			signature = walletSession.personalSign(walletAddress, loginProof())
		} catch (e: Exception) {
			throw PersonalSignException(e)
		}
		Timber.d("---------- Input ----------")
		Timber.e("signature: $signature")
		val userDto: UserDto?
		try {
			userDto = networkDatasource.login(LoginRequestBody(signature))
			Timber.d("---------- Output ----------")
			Timber.e("userDto: $userDto")
			sessionData.user = userDto.mapToKycUser()
		} catch (e: KycNetworkCallException) {

		}
	}

	/**
	 * Save the personal information of the user.
	 * If the provided email address is not yet confirmed then a confirmation email will be sent.
	 *
	 * @param personalData The personal information required, wrapped in a [PersonalData] class
	 */
	suspend fun setPersonalData(personalData: PersonalData) {
		if (!disclaimerAccepted)
			throw VerificationSessionIllegalAction(IllegalAction.DisclaimerNotAccepted)

		updateUserPersonalData(personalData)
	}

	/**
	 * Authorizes the minting process for a selected NFT image
	 * Returns after the minting of the selected NFT has been authorized
	 *
	 * The list of available images can be acquired by calling the [getNFTImages] function.
	 *
	 * @param selectedImage the ID of the NFT image that is about to be minted
	 */
	suspend fun requestMinting(selectedImage: String) {
		Timber.d("Selected Nft Image: $selectedImage")
		authorizeMintingOfNFT(selectedImage)
		checkAuthorizeMinting()
	}

	/**
	 * Mints the previously chosen NFT image
	 *
	 * This method can only be called successfully after the user was [authorized][requestMinting] for minting with a selected image
	 *
	 * @return An URL for an explorer where the minting transaction can be viewed
	 *
	 * @param performTransaction Lambda function which contains the actual minting call, using the WalletSession interface
	 */
	suspend fun mint(): MintingResult {
		val authCode =
			authorizeMintingResponse?.code
				?: throw VerificationSessionIllegalAction(IllegalAction.AuthorizationMissing)
		val mintingFunction = MintFunction(authCode)

		val transactionProperties = createMintingPropertiesFor(mintingFunction)
		val result: MintingTransactionResult?
		try {
			result = walletSession.sendMintingTransaction(walletAddress, transactionProperties)
		} catch (e: Exception) {
			throw SendTransactionException(e)
		}
		Timber.d("Receipt hash: ${result.txHash}")
		val receipt = continueWhenTransactionFinished(result.txHash)
		val tokenId: String?
		try {
			tokenId = receipt.transactionReceipt.get().logs[0].topics[3]
		} catch (e: Exception) {
			throw GenericKycException(
				"Token id not found inside the transaction receipt",
				e.cause
			)
		}
		val decimalTokenId = tokenId.convertBigInteger().toString(10)
		tokenMinted(authCode, decimalTokenId, result.txHash)

		this.authorizeMintingResponse = null

		val urlString = network.explorer.url + network.explorer.transaction_path + result.txHash
		return MintingResult(
			explorerURL = if (URLUtil.isValidUrl(urlString)) Uri.parse(urlString) else null,
			transactionId = result.txHash,
			tokenId = tokenId
		)

	}

	/**
	 * Starts the identity verification process.
	 *
	 * @param activity The activity that starts the new activity containing the persona process
	 *
	 * @return The result of the identity verification flow. It only tells whether the user completed the identity flow or cancelled it. Information regarding the validity of the identity verification can be accessed at [verificationStatus]
	 * @see resumeOnVerificationCompleted
	 */
	suspend fun startIdentification(activity: ComponentActivity): IdentityFlowResult {
		if (!emailConfirmed)
			throw VerificationSessionIllegalAction(IllegalAction.EmailIsNotConfirmed)

		val referenceID = sessionData.user.extId ?: throw PersonaException.ExternalIdMissing()
		val identityVerificationUseCase = CustomKoinComponent().get<IdentityVerificationUseCase>()
		val identificationResult = suspendCoroutine<InquiryResponse> { continuation ->
			identityVerificationUseCase(
				referenceID = referenceID,
				templateId = personaData.templateID,
				activity = activity,
				resultContinuation = continuation,
				environment = personaData.environment
			)
		}
		return when (identificationResult) {
			is InquiryResponse.Complete -> IdentityFlowResult.COMPLETED
			is InquiryResponse.Cancel -> IdentityFlowResult.CANCELLED
			is InquiryResponse.Error -> throw PersonaException.PersonaVerificationFailed()
		}

	}

	/**
	 * Accepts the disclaimer if the disclaimer hasn't benn accepted before.
	 */
	suspend fun acceptDisclaimer() {
		Timber.d("sending disclaimer acceptance")
		try {
			networkDatasource.saveDisclaimer()
		} catch (e: KycNetworkCallException) {
			if (e.networkException is NetworkErrorResponse.ApiError) {
				if (e.networkException.body.error_code != "DisclaimerAlreadyAccepted") {
					throw e
				}
			} else {
				throw e
			}
		}
		refreshUser()

	}

	private fun createMintingPropertiesFor(function: Function): MintingProperties {
		val gasPrice = calculateGasPriceFor(function)
		val resolvedContractAddress = kycConfig?.address ?: throw ConfigNotFoundException(
			network = network.name,
			verificationType = VerificationType.KYC
		)
		return MintingProperties(
			contractAddress = resolvedContractAddress,
			contractABI = FunctionEncoder.encode(function),
			gasAmount = gasPrice.amount.asHexString(),
			gasPrice = gasPrice.price.asHexString(),
		)
	}

	/**
	 * Creates an estimation for the gas fees during minting
	 *
	 * @return The gas fee estimation wrapped in [GasEstimation]
	 */
	fun estimateGasForMinting(): GasEstimation {
		val authCode =
			authorizeMintingResponse?.code
				?: throw VerificationSessionIllegalAction(IllegalAction.AuthorizationMissing)
		val mintingFunction = MintFunction(authCode)
		return calculateGasPriceFor(mintingFunction)
	}

	/**
	 * Sends a confirmation email to the [provided][setPersonalData] email address if the address in question has not been confirmed previously
	 *
	 * @see resumeOnEmailConfirmed
	 */
	suspend fun sendConfirmationEmail() {
		Timber.d("Confirmation email sent")
		try {
			networkDatasource.sendEmailConfirm()
		} catch (e: KycNetworkCallException) {
			if (e.networkException is NetworkErrorResponse.ApiError) {
				if (e.networkException.body.error_code == "EmailAlreadyConfirmed") {
					throw EmailIsAlreadyConfirmed()
				}
			}
			throw e
		}
	}

	private fun calculateGasPriceFor(function: Function): GasEstimation {
		return GasEstimation.estimate(
			amount = estimateGasUse(function),
			price = getGasPrice(),
			gasCurrency = network.native_currency
		)
	}

	private fun getGasPrice(): BigInteger {
		val ethGasPrice = web3j.ethGasPrice().sendAsync().get()
		if (ethGasPrice.error != null) {
			throw ethGasPrice.error.toException()
		}
		return ethGasPrice.gasPrice
	}

	private fun estimateGasUse(function: Function): BigInteger {
		val transaction = Transaction.createFunctionCallTransaction(
			walletAddress,
			null,
			null,
			null,
			kycConfig?.address,
			FunctionEncoder.encode(function)
		)
		val ethEstimateGas = web3j.ethEstimateGas(transaction).sendAsync().get()
		if (ethEstimateGas.error != null) {
			throw ethEstimateGas.error.toException()
		}
		return ethEstimateGas.amountUsed
	}

	private suspend fun checkAuthorizeMinting() {
		val mintingTxHash = authorizeMintingResponse?.tx_hash ?: throw VerificationSessionIllegalAction(
			IllegalAction.AuthorizationMissing
		)
		continueWhenTransactionFinished(mintingTxHash)
	}

	private suspend fun refreshUser() {
		val updatedUser = networkDatasource.getUser().mapToKycUser()
		sessionData.user = updatedUser
	}

	private suspend fun updateUserPersonalData(personalData: PersonalData) {
		if (requiredInformationProvided) {
			val userDto = networkDatasource.updateUser(
				UpdateUserRequestBody(
					email = personalData.email,
					residency = personalData.residency,
					legal_entity = personalData.isLegalEntity,
				)
			)
			val userFromNetwork = userDto.mapToKycUser()
			sessionData.user = userFromNetwork
		} else {
			Timber.e("Missed user information")
		}
	}


	/**
	 * Starts polling the backend and suspends until the email is confirmed.
	 *
	 * @see sendConfirmationEmail
	 */
	suspend fun resumeOnEmailConfirmed() {
		emailPollJob?.cancel()
		var timeOutCounter = 0
		emailPollJob = scope.launch {
			while (true) {
				if(timeOutCounter>100){
					throw SuspensionTimeOutException
				}else {timeOutCounter++}
				val kycNetworkUser: User?
				try {
					kycNetworkUser = networkDatasource.getUser().mapToKycUser()
				} catch (e: KycNetworkCallException) {
					emailPollJob?.cancel()
					throw e
				}
				if (kycNetworkUser.isEmailConfirmed()) {
					sessionData.user = kycNetworkUser
					Timber.d("email confirmed")
					break
				} else {
					Timber.d("email not confirmed, retry in $EMAIL_CONFIRMED_VERIFICATION_POLL_INTERVAL ms")
					delay(EMAIL_CONFIRMED_VERIFICATION_POLL_INTERVAL)
				}
			}
		}
		emailPollJob?.join()
	}

	/**
	 * Starts polling the backend and suspends until the identity verification result is available.
	 *
	 * @see startIdentification
	 */
	suspend fun resumeWhenIdentified() {
		verificationPollJob?.cancel()
		var timeOutCounter = 0
		verificationPollJob = scope.launch {
			while (true) {
				if(timeOutCounter>100){
					throw SuspensionTimeOutException
				}else {timeOutCounter++}
				val networkKycUser: User?
				try {
					networkKycUser = networkDatasource.getUser().mapToKycUser()
				} catch (e: KycNetworkCallException) {
					verificationPollJob?.cancel()
					throw e
				}
				if (networkKycUser.isIdentityVerified()) {
					sessionData.user = networkKycUser
					break
				} else {
					Timber.d("user not verified, retry in $IDENTITY_VERIFICATION_POLL_INTERVAL ms")
					delay(IDENTITY_VERIFICATION_POLL_INTERVAL)
				}
			}
		}
		verificationPollJob?.join()
	}

	/**
	 * Calling this function lets you update your email address associated to your account.
	 *
	 * Unless the user is logged in and has provided all necessary information, the function will not run successfully.
	 */
	suspend fun updateEmail(email: String){
		if(!loggedIn) throw VerificationSessionIllegalAction(IllegalAction.NotLoggedIn)

		val residency = sessionData.user.residency
		val isLegalEntity = sessionData.user.isLegalEntity
		if(!requiredInformationProvided || residency == null || isLegalEntity == null)
			throw VerificationSessionIllegalAction(IllegalAction.PersonalInformationMissing)

		val personalData = PersonalData(
			email = email,
			residency = residency,
			isLegalEntity = isLegalEntity
		)
		updateUserPersonalData(personalData)
	}


	private suspend fun authorizeMintingOfNFT(selectedNftId: String) {
		val blockchainAccount =
			sessionData.user.blockchainAccounts.firstOrNull() ?: throw NoBlockChainAccountFound()

		Timber.d("---------- Input ----------")
		Timber.d("selectedNftId: $selectedNftId")
		Timber.d("blockchainAccount: $blockchainAccount")

		val authorizeMintingResponse: AuthorizeMintingResponse = networkDatasource.authorizeMinting(
			AuthorizeMintingRequestBody(
				network = network.id,
				blockchain_account_id = blockchainAccount.id,
				selected_image_id = selectedNftId
			)
		)

		Timber.d("---------- Output ----------")
		Timber.d("authorizeMintingResponse: $authorizeMintingResponse")

		this.authorizeMintingResponse = authorizeMintingResponse
	}

	private suspend fun continueWhenTransactionFinished(txHash: String): EthGetTransactionReceipt {
		var timeOutCounter = 0
		while (true) {
			if(timeOutCounter>50){
				throw SuspensionTimeOutException
			}else {timeOutCounter++}
			delay(5.seconds)
			Timber.d("checking if transaction finsihed")
			val receipt = getTransactionReceipt(txHash)
			if (receipt.transactionReceipt.isPresent) {
				val transactionReceipt = receipt.transactionReceipt.get()
				val success = transactionReceipt.status == "0x1"
				if (!success) {
					throw receipt.error.toException()
				}
				Timber.d("Transaction finished")
				return receipt
			}
		}
	}


	private fun getTransactionReceipt(txHash: String): EthGetTransactionReceipt {
		return web3j.ethGetTransactionReceipt(txHash).sendAsync().get()
	}

	private suspend fun tokenMinted(authCode: String, tokenId: String, txHash: String) {
		val mintTokenBody = MintTokenBody(
			authorization_code = authCode,
			token_id = tokenId,
			minting_tx_id = txHash
		)
		networkDatasource.sendMintToken(mintTokenBody)
	}

}
