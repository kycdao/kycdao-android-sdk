package com.kycdao.android.sdk.verificationSession

import android.net.Uri
import android.webkit.URLUtil
import androidx.activity.ComponentActivity
import com.bitraptors.networking.api.models.NetworkErrorResponse
import com.kycdao.android.sdk.CustomKoinComponent
import com.kycdao.android.sdk.contract.ERC721Contract
import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.dto.TokenDetailsDto
import com.kycdao.android.sdk.dto.UserDto
import com.kycdao.android.sdk.exceptions.*
import com.kycdao.android.sdk.model.*
import com.kycdao.android.sdk.model.functions.*
import com.kycdao.android.sdk.model.functions.mint.MintFunction
import com.kycdao.android.sdk.model.functions.mint.MintingProperties
import com.kycdao.android.sdk.model.functions.mint.MintingTransactionResult
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.request_models.AuthorizeMintingRequestBody
import com.kycdao.android.sdk.network.request_models.LoginRequestBody
import com.kycdao.android.sdk.network.request_models.MintTokenBody
import com.kycdao.android.sdk.network.request_models.UpdateUserRequestBody
import com.kycdao.android.sdk.usecase.IdentityVerificationUseCase
import com.kycdao.android.sdk.util.*

import com.kycdao.android.sdk.wallet.WalletSession
import com.withpersona.sdk2.inquiry.InquiryResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.web3j.abi.FunctionEncoder
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt
import timber.log.Timber
import java.math.BigDecimal
import java.math.BigInteger
import java.net.URI
import java.util.*
import kotlin.coroutines.suspendCoroutine

/**
 * A verification session object which contains session related data and session related operations
 *
 * Instances should be created by calling the VerificationManager-s createSession function.
 */
data class VerificationSession internal constructor(
	/**
	 * 	Wallet address used to create the session
	 */
	val walletAddress: String,
	private val network: Network,
	private val kycConfig: SmartContractConfig?,
	private val accreditedConfig: SmartContractConfig?,
	private val personaData: Persona,
	private var sessionData: SessionData,
	internal val rpcURL: String,
	/**
	 * The wallet session associated with this verification session, used to communicate with a wallet
	 */
	val walletSession: WalletSession,
	private var authorizeMintingResponse: AuthorizeMintingResponse? = null,
) : CustomKoinComponent() {

	private companion object {
		private val IDENTITY_VERIFICATION_POLL_INTERVAL = 10.seconds
		private val EMAIL_CONFIRMED_VERIFICATION_POLL_INTERVAL = 10.seconds
	}

	private val web3j: Web3j by inject() {
		parametersOf(rpcURL)
	}

	/**
	 * The contents of the disclaimer that the user is required to accept to use the service.
	 * It can be accepted by calling the [acceptDisclaimer] function.
	 */
	val disclaimerText = Constants.disclaimerText

	/**
	 * An URL pointing to the terms of service document.
	 */
	val termsOfService = URI.create("https://kycdao.xyz/terms-and-conditions")

	/**
	 * An URL pointing to the privacy policy document.
	 */
	val privacyPolicy = URI.create("https://kycdao.xyz/privacy-policy")

	private val networkDatasource: NetworkDatasource by inject()
	private var scope = CoroutineScope(Dispatchers.IO)

	val hasMembership: Boolean get() = sessionData.user.hasMembership

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
	val currentEmail: String?
		get() = sessionData.user.email

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

	private var personaSessionData: PersonaSessionData? = null


	private val loginProof: String
		get() = "kycDAO-login-${sessionData.nonce}"


	/**
	 * Provides the user selectable NFT images
	 * @return A list of image related data
	 */
	fun getNFTImages(): List<TokenImage> {
		return sessionData.user.availableImages.filter { it.imageType == ImageType.Identicon }

	}

	/**
	 * Regenerates the list of available NFT images
	 * @return A list of the newly generated images
	 */
	suspend fun regenerateNFTImages(): List<TokenImage> {
		Timber.d("ORiginal: ${getNFTImages()}")

		networkDatasource.getNewIdenticons()
		refreshUser()

		return getNFTImages()
	}


	/**
	 * Logs in the user to the current session
	 * The user will be redirected to their wallet, where they will have to sign a session data in order to login
	 */
	suspend fun login() {
		val signature: String?
		try {
			signature = walletSession.personalSign(walletAddress, loginProof)
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
			if (e.networkException is NetworkErrorResponse.ApiError) {
				if (e.networkException.body.error_code != "SessionUserAlreadyExists") {
					throw UserAlreadyLoggedIn()
				}
			} else {
				throw e
			}
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
	 * @param membershipDuration the number of years the membership is requested.
	 */
	suspend fun requestMinting(selectedImage: String, membershipDuration: UInt) {
		Timber.d("Selected Nft Image: $selectedImage")
		authorizeMintingOfNFT(selectedImage, membershipDuration)
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
		val mintingFunction = ABIFunction<Nothing>(
			function = MintFunction(authCode),
			contractAddress = getKycContractAddress(),
			walletAddress = walletAddress
		)
		Timber.d("AUTHCODE: $authCode")
		val requiredMintCost = getRequiredMintCostForCode(authCode)

		val transactionProperties = createMintingPropertiesFor(mintingFunction, requiredMintCost)
		val result: MintingTransactionResult?
		try {
			result = walletSession.sendMintingTransaction(walletAddress, transactionProperties)
		} catch (e: Exception) {
			throw SendTransactionException(e)
		}

		Timber.d("Receipt hash: ${result.txHash}")

		val receipt = continueWhenTransactionFinished(result.txHash)
		Timber.d("LOGS: ${receipt.transactionReceipt.get().logs}")
		var tokenId: String? = null
		try {
			for (log in receipt.transactionReceipt.get().logs) {
				val result = ERC721Contract.getTopicID(log)
				if (result != null) {
					tokenId = result
					break
				}
			}
		} catch (e: Exception) {
			throw GenericKycException(
				"Token id not found inside the transaction receipt",
				e.cause
			)
		}
		if (tokenId == null) {
			throw GenericKycException(
				"Token id not found inside the transaction receipt", null
			)
		}
		Timber.d("TOKENID: $tokenId")
		val tokenDetails = tokenMinted(authCode, tokenId, result.txHash)

		this.authorizeMintingResponse = null

		val urlString = network.explorer.url + network.explorer.transaction_path + result.txHash
		return MintingResult(
			explorerURL = if (URLUtil.isValidUrl(urlString)) Uri.parse(urlString) else null,
			transactionId = result.txHash,
			tokenId = tokenId,
			imageURL = tokenDetails.image_url
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
				environment = personaData.environment,
				personaSessionData = personaSessionData
			)
		}
		if (identificationResult is InquiryResponse.Cancel) {
			identificationResult.inquiryId?.let { inquiryId ->
				personaSessionData = PersonaSessionData(
					referenceId = referenceID,
					inquiryId = inquiryId,
					sessionToken = identificationResult.sessionToken
				)
			}
		} else {
			personaSessionData = null
		}
		refreshUser()
		Timber.d("IDENTIFICATIONRES: ${identificationResult is InquiryResponse.Complete}")
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

	private suspend fun createMintingPropertiesFor(
		function: ABIFunction<*>,
		requiredMintCost: BigInteger
	): MintingProperties {
		val gasPrice = calculateGasPriceFor(function.toTransaction(requiredMintCost))
		val resolvedContractAddress = getKycContractAddress()
		Timber.d("COST:" + requiredMintCost.toText(network.native_currency))
		return MintingProperties(
			contractAddress = resolvedContractAddress,
			contractABI = FunctionEncoder.encode(function.function),
			gasAmount = gasPrice.amount.asHexString(),
			gasPrice = gasPrice.price.asHexString(),
			paymentAmount = requiredMintCost.asHexString()
		)
	}

	/**
	 * Creates an estimation for the fee of minting.
	 *
	 * @return The estimated fee wrapped in a [PriceEstimation]
	 */
	suspend fun getMintingPrice(): PriceEstimation {
		val authCode =
			authorizeMintingResponse?.code
				?: throw VerificationSessionIllegalAction(IllegalAction.AuthorizationMissing)
		val mintingFunction = ABIFunction<Nothing>(
			function = MintFunction(authCode),
			contractAddress = getKycContractAddress(),
			walletAddress = walletAddress
		)
		val requiredMintCost = getRequiredMintCostForCode(authCode)
		val gasEstimation = estimateGasUse(mintingFunction.toTransaction(requiredMintCost))
		return PriceEstimation(
			paymentAmount = requiredMintCost,
			gasFee = gasEstimation,
			currency = network.native_currency
		)
	}

	/**
	 * Sends a confirmation email to the [provided][setPersonalData] email address if the address in question has not been confirmed previously.
	 *
	 * @see resumeOnEmailConfirmed
	 */
	suspend fun resendConfirmationEmail() {
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

	private suspend fun calculateGasPriceFor(transaction: Transaction): GasEstimation {
		return GasEstimation.estimate(
			amount = estimateGasUse(transaction),
			price = getGasPrice(),
			gasCurrency = network.native_currency
		)
	}

	private suspend fun getGasPrice(): BigInteger {
		val ethGasPrice = web3j.ethGasPrice().sendAsync().await()
		if (ethGasPrice.error != null) {
			throw ethGasPrice.error.toException()
		}
		return ethGasPrice.gasPrice
	}

	private suspend fun estimateGasUse(transaction: Transaction): BigInteger {
		val ethEstimateGas = web3j.ethEstimateGas(transaction).sendAsync().await()
		if (ethEstimateGas.error != null) {
			throw ethEstimateGas.error.toException()
		}
		return ethEstimateGas.amountUsed
	}

	private suspend fun checkAuthorizeMinting() {
		val mintingTxHash =
			authorizeMintingResponse?.tx_hash ?: throw VerificationSessionIllegalAction(
				IllegalAction.AuthorizationMissing
			)
		continueWhenTransactionFinished(mintingTxHash)
	}

	private suspend fun refreshUser() {
		val updatedUser = networkDatasource.getUser().mapToKycUser()
		sessionData.user = updatedUser
	}

	private suspend fun refreshSession() {
		val updatedSession = networkDatasource.getSession().mapToSessionData()
		sessionData = updatedSession
	}

	private suspend fun updateUserPersonalData(personalData: PersonalData) {
		val userDto = networkDatasource.updateUser(
			UpdateUserRequestBody(
				email = personalData.email,
				residency = personalData.residency,
				legal_entity = false,
			)
		)
		val userFromNetwork = userDto.mapToKycUser()
		sessionData.user = userFromNetwork
	}


	/**
	 * Starts polling the backend and suspends until the email is confirmed.
	 *
	 * After a 100 retries it stops with a [SuspensionTimeOutException].
	 *
	 * @see sendConfirmationEmail
	 */
	suspend fun resumeOnEmailConfirmed() {
		emailPollJob?.cancel()
		var timeOutCounter = 0
		emailPollJob = scope.launch {
			while (true) {
				if (timeOutCounter > 100) {
					throw SuspensionTimeOutException
				} else {
					timeOutCounter++
				}
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
	 * After a 100 retries it stops with a [SuspensionTimeOutException].
	 * @see startIdentification
	 */
	suspend fun resumeOnVerificationCompleted() {
		verificationPollJob?.cancel()
		var timeOutCounter = 0
		verificationPollJob = scope.launch {
			while (true) {
				if (timeOutCounter > 100) {
					throw SuspensionTimeOutException
				} else {
					timeOutCounter++
				}
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
	suspend fun updateEmail(email: String) {
		if (!loggedIn) throw VerificationSessionIllegalAction(IllegalAction.NotLoggedIn)

		val residency = sessionData.user.residency
		val isLegalEntity = sessionData.user.isLegalEntity
		if (!requiredInformationProvided || residency == null || isLegalEntity == null)
			throw VerificationSessionIllegalAction(IllegalAction.PersonalInformationMissing)

		val personalData = PersonalData(
			email = email,
			residency = residency,
		)
		updateUserPersonalData(personalData)
	}


	private suspend fun authorizeMintingOfNFT(selectedNftId: String, membershipDuration: UInt) {
		val blockchainAccount =
			sessionData.user.blockchainAccounts.firstOrNull() ?: throw NoBlockChainAccountFound()

		Timber.d("---------- Input ----------")
		Timber.d("selectedNftId: $selectedNftId")
		Timber.d("blockchainAccount: $blockchainAccount")

		val authorizeMintingResponse: AuthorizeMintingResponse = networkDatasource.authorizeMinting(
			AuthorizeMintingRequestBody(
				accountId = blockchainAccount.id,
				network = network.id,
				verificationType = VerificationType.KYC,
				selected_image_id = selectedNftId,
				subscriptionDuration = membershipDuration

			)
		)

		Timber.d("---------- Output ----------")
		Timber.d("authorizeMintingResponse: $authorizeMintingResponse")

		this.authorizeMintingResponse = authorizeMintingResponse
	}

	private suspend fun continueWhenTransactionFinished(txHash: String): EthGetTransactionReceipt {
		var timeOutCounter = 0
		while (true) {
			if (timeOutCounter > 100) {
				throw SuspensionTimeOutException
			} else {
				timeOutCounter++
			}
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


	private suspend fun getTransactionReceipt(txHash: String): EthGetTransactionReceipt {
		return web3j.ethGetTransactionReceipt(txHash).sendAsync().await()
	}

	private suspend fun tokenMinted(
		authCode: String,
		tokenId: String,
		txHash: String
	): TokenDetailsDto {
		val mintTokenBody = MintTokenBody(
			authorization_code = authCode,
			token_id = tokenId,
			minting_tx_id = txHash
		)
		return networkDatasource.sendMintToken(mintTokenBody)
	}

	/**
	 * Returns how much the subscription costs per year in USD.
	 *
	 * @return the price of subscription per year in USD
	 */
	suspend fun getMembershipCostPerYearText(): String {
		val getSubscriptionCostFunction = ABIFunction<BigInteger>(
			function = KYCGetSubscriptionCostPerYearUSDFunction(),
			contractAddress = getKycContractAddress(),
			walletAddress = walletAddress
		)
		val getDecimalFunction = ABIFunction<BigInteger>(
			function = KYCGetSubscriptionCostDecimalFunction(),
			contractAddress = getKycContractAddress(),
			walletAddress = walletAddress
		)
		val rawPrice = scope.async { web3j.callABIFunction(getSubscriptionCostFunction) }
		val decimal = scope.async { web3j.callABIFunction(getDecimalFunction) }
		val divider = BigInteger.TEN.pow(decimal.await().intValueExact())
		val price = rawPrice.await().toBigDecimal().divide(divider.toBigDecimal())
		return price.toString()
	}

	private suspend fun getRawRequiredMintCostForCode(authCode: String): BigInteger {
		val getRequiredMintingCostFunction = ABIFunction<BigInteger>(
			function = KYCGetRequiredMintCostForCodeFunction(authCode, walletAddress),
			contractAddress = getKycContractAddress(),
			walletAddress = walletAddress
		)
		return web3j.callABIFunction(getRequiredMintingCostFunction)
	}

	private suspend fun getRequiredMintCostForCode(authCode: String): BigInteger {
		val mintingCost = getRawRequiredMintCostForCode(authCode)
		val withSlippage = (mintingCost.toBigDecimal().times(BigDecimal("1.1"))).toBigInteger()
		return withSlippage
	}

	private suspend fun getRequiredMintCostForYears(years: UInt): BigInteger {
		if (years < 1u) throw VerificationSessionIllegalAction(IllegalAction.YearIsZero)

		val discountYears = sessionData.discountYears ?: 0
		val yearsToPay = years - discountYears.toUInt()
		val timeInSeconds = yearsToPay.yearsInSeconds

		val getRequiredMintingCostFunction = ABIFunction<BigInteger>(
			function = KYCGetRequiredMintCostForSecondsFunction(timeInSeconds),
			contractAddress = getKycContractAddress(),
			walletAddress = walletAddress,
		)
		return web3j.callABIFunction(getRequiredMintingCostFunction)
	}

	/**
	 * Estimates the price based on how long the user wishes to subscribe.
	 *
	 * @return the estimated costs wrapped in a [PaymentEstimation] object.
	 */
	suspend fun estimatePayment(yearsPurchased: Int): PaymentEstimation {
		refreshSession()
		val membershipPayment = getRequiredMintCostForYears(yearsPurchased.toUInt())
		val discountYears = sessionData.discountYears ?: 0
		return PaymentEstimation(
			paymentAmount = membershipPayment,
			discountYears = discountYears.toUInt(),
			currency = network.native_currency
		)
	}

	private fun getKycContractAddress(): String {
		return kycConfig?.address ?: throw ConfigNotFoundException(
			network.name,
			verificationType = VerificationType.KYC
		)
	}

}
