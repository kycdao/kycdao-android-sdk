package com.kycdao.android.sdk.kycSession

import android.net.Uri
import android.webkit.URLUtil
import androidx.activity.ComponentActivity
import com.kycdao.android.sdk.KoinContainer.identityVerificationUseCase
import com.kycdao.android.sdk.KoinContainer.networkDatasource
import com.kycdao.android.sdk.KoinContainer.web3j
import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.model.*
import com.kycdao.android.sdk.network.api.AuthorizeMintingRequestBody
import com.kycdao.android.sdk.network.api.LoginRequestBody
import com.kycdao.android.sdk.network.api.MintTokenBody
import com.kycdao.android.sdk.network.api.UpdateUserRequestBody
import com.kycdao.android.sdk.util.asHexString
import com.kycdao.android.sdk.util.convertBigInteger
import com.kycdao.android.sdk.util.seconds
import com.kycdao.android.sdk.wallet.WalletSession
import kotlinx.coroutines.*
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt
import timber.log.Timber
import java.math.BigInteger
import java.util.*

data class KycSession(
	val walletAddress: String,
	private val network: Network,
	private val kycConfig: SmartContractConfig?,
	private val accreditedConfig: SmartContractConfig?,
	val personaData: Persona,
	private val sessionData: SessionData,
	val walletSession: WalletSession,
	val walletConnected: Boolean = false,
	val signature: String? = null,
	private var authorizeMintingResponse: AuthorizeMintingResponse? = null,
) {

	companion object {
		private val IDENTITY_VERIFICATION_POLL_INTERVAL = 10.seconds
		private val EMAIL_CONFIRMED_VERIFICATION_POLL_INTERVAL = 10.seconds
	}

	private var scope = CoroutineScope(Dispatchers.IO)

	val id: String = UUID.randomUUID().toString()
	val chainId: String
		get() = network.caip2id
	val disclaimerAccepted: Boolean
		get() = sessionData.user.disclaimerAccepted?.isNotEmpty() ?: false
	val emailConfirmed: Boolean
		get() = sessionData.user.isEmailConfirmed()
	val loggedIn: Boolean
		get() = sessionData.user.id != null
	private val residencyProvided: Boolean
		get() = sessionData.user.residency?.isNotEmpty() ?: false
	private val emailProvided: Boolean
		get() = sessionData.user.email?.isNotEmpty() ?: false
	val requiredInformationProvided: Boolean
		get() = residencyProvided && emailProvided && sessionData.user.isLegalEntity != null
//	val verificationStatus: VerificationStatus
//		get() = sessionData.user.verificationStatus


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

	fun getNFTImages(): List<TokenImage> {
		return sessionData.user.availableImages.filter { it.imageType == ImageType.Identicon }
	}


	/**
	 * Logs in
	 *
	 * @param signature Personal signed signature created by WalletSession.personalSign()
	 *
	 * @return Returns whether the login was successful or not
	 */
	suspend fun login() {
		// TODO handle network error
		if (sessionData.user.id == null) {
			val signature = walletSession.personalSign(walletAddress, loginProof())
			Timber.d("---------- Input ----------")
			Timber.e("signature: $signature")
			val userDto = networkDatasource.login(LoginRequestBody(signature))
			Timber.d("---------- Output ----------")
			Timber.e("userDto: $userDto")
			sessionData.user = userDto.mapToKycUser()
		}
	}

	/**
	 * Save the personal information of the user and sends confirmation email if email is not yet confirmed.
	 *
	 * By calling this function the disclaimer is also accepted.
	 * The function finishes when the email is confirmed
	 *
	 * @param personalData The personal information to be saved wrapped in a PersonalDataResult class
	 */
	suspend fun setPersonalData(personalData: PersonalData) {
		sessionData.user.apply {
			email = personalData.email
			residency = personalData.residency
			isLegalEntity = personalData.isLegalEntity
		}
		updateUserPersonalData()
		if (!emailConfirmed) {
			sendConfirmationEmail()
		}
	}

	/**
	 * Launches the predefined NftSelector activity on which, and returns with the id of the selectedImage
	 *
	 * Returns after the minting of the selectedNft has been authorized
	 *
	 * @param activity Activity that launches the new NftSelectorActivity
	 *
	 * @return selectedImage Id
	 */
	suspend fun requestMinting(selectedImage: String) {
		Timber.d("Selected Nft Image: $selectedImage")
		authorizeMintingOfNFT(selectedImage)
		checkAuthorizeMinting()
	}

	/**
	 * Performs a minting operation
	 *
	 * @param performTransaction Lambda function which contains the actual minting call, using the WalletSession interface
	 */
	suspend fun mint(): Uri? {
		val authCode =
			authorizeMintingResponse?.code ?: throw Exception("No auth code found")
		val mintingFunction = MintFunction(authCode)
		val transactionProperties = createMintingPropertiesFor(mintingFunction)
		val result = walletSession.sendMintingTransaction(walletAddress, transactionProperties)
		Timber.d("Receipt hash: ${result.txHash}")
		val receipt = continueWhenTransactionFinished(result.txHash)

		val tokenId = receipt.transactionReceipt.get().logs[0].topics[3]
		val decimalTokenId = tokenId.convertBigInteger().toString(10)
		tokenMinted(authCode, decimalTokenId, result.txHash)

		this.authorizeMintingResponse = null

		val urlString = network.explorer.url + network.explorer.transaction_path + result.txHash
		return if (URLUtil.isValidUrl(urlString)) {
			Uri.parse(urlString)
		} else {
			null
		}
	}

	/**
	 * Starts the persona identification process.
	 *
	 * @param activity The activity that starts the new activity containing the persona process
	 */
	fun startIdentification(activity: ComponentActivity) {
		val templateID = personaData.templateID
		val referenceID = sessionData.user.extId
		identityVerificationUseCase(
			referenceID = referenceID, templateId = templateID, activity = activity
		) {
			resumeOnVerificationCompleted()
			Timber.d("verification Finished")
		}
	}

	private fun createMintingPropertiesFor(function: Function): MintingProperties {
		val gasPrice = calculateGasPrice(function)
		val resolvedContractAddress = kycConfig?.address ?: throw Exception()
		return MintingProperties(
			contractAddress = resolvedContractAddress,
			contractABI = FunctionEncoder.encode(function),
			gasAmount = gasPrice.amount.asHexString(),
			gasPrice = gasPrice.finalGasPrice.asHexString(),
		)
	}

	private fun calculateGasPrice(function: Function): GasPriceEstimation {
		return GasPriceEstimation(
			amount = estimateGasUse(function),
			price = getGasPrice(),
			currency = network.native_currency
		)
	}

	private fun getGasPrice(): BigInteger {
		val ethGasPrice = web3j.ethGasPrice().sendAsync().get()
		if (ethGasPrice.error != null) {
			Timber.d("error.code: ${ethGasPrice.error?.code}")
			Timber.d("error.data: ${ethGasPrice.error?.data}")
			Timber.d("error.message: ${ethGasPrice.error?.message}")
			// TODO error
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
			Timber.d("error.code: ${ethEstimateGas.error?.code}")
			Timber.d("error.data: ${ethEstimateGas.error?.data}")
			Timber.d("error.message: ${ethEstimateGas.error?.message}")
			// TODO handle error
		}
		return ethEstimateGas.amountUsed
	}

	private suspend fun checkAuthorizeMinting() {
		val mintingTxHash = authorizeMintingResponse?.tx_hash ?: throw Exception()
		continueWhenTransactionFinished(mintingTxHash)
	}

	private suspend fun sendConfirmationEmail() {
		Timber.d("Confirmation email sent")
		networkDatasource.sendEmailConfirm()
	}

	suspend fun refreshUser() {
		val updatedUser = networkDatasource.getUser().mapToKycUser()
		sessionData.user = updatedUser
	}

	private suspend fun updateUserPersonalData() {
		if (requiredInformationProvided) {
			val userDto = networkDatasource.updateUser(
				UpdateUserRequestBody(
					email = sessionData.user.email!!,
					residency = sessionData.user.residency!!,
					legal_entity = sessionData.user.isLegalEntity!!,
				)
			)
			val userFromNetwork = userDto.mapToKycUser()
			sessionData.user = userFromNetwork
		} else {
			Timber.e("Missed user information")
		}
	}

	suspend fun acceptDisclaimer() {
		if (!disclaimerAccepted) {
			Timber.d("sending disclaimer acceptance")
			networkDatasource.saveDisclaimer()
			refreshUser()
		} else {
			Timber.d("disclaimer acceptance has already been sent before")
		}
	}

	private var emailPollJob: Job? = null
	private var verificationPollJob: Job? = null
	fun resumeOnEmailConfirmed() {
		emailPollJob?.cancel()
		emailPollJob = scope.launch {
			while (true) {
				val networkKycUser = networkDatasource.getUser().mapToKycUser()
				if (networkKycUser.isEmailConfirmed()) {
					sessionData.user = networkKycUser
					Timber.d("email confirmed")
					break
				} else {
					Timber.d("email not confirmed, retry in $EMAIL_CONFIRMED_VERIFICATION_POLL_INTERVAL ms")
					delay(EMAIL_CONFIRMED_VERIFICATION_POLL_INTERVAL)
				}
			}
		}
	}

	fun resumeOnVerificationCompleted() {
		verificationPollJob?.cancel()
		verificationPollJob = scope.launch {
			while (true) {
				val networkKycUser = networkDatasource.getUser().mapToKycUser()
				if (networkKycUser.isIdentityVerified()) {
					sessionData.user = networkKycUser
					break
				} else {
					Timber.d("user not verified, retry in $IDENTITY_VERIFICATION_POLL_INTERVAL ms")
					delay(IDENTITY_VERIFICATION_POLL_INTERVAL)
				}
			}
		}
	}


	private suspend fun authorizeMintingOfNFT(selectedNftId: String) {
		val blockchainAccount = sessionData.user.blockchainAccounts.first()

		Timber.d("---------- Input ----------")
		Timber.d("selectedNftId: $selectedNftId")
		Timber.d("blockchainAccount: $blockchainAccount")

		val authorizeMintingResponse: AuthorizeMintingResponse = networkDatasource.authorizeMinting(
			AuthorizeMintingRequestBody(
				//network is currently baked in
				blockchain_account_id = blockchainAccount.id,
				selected_image_id = selectedNftId
			)
		)

		Timber.d("---------- Output ----------")
		Timber.d("authorizeMintingResponse: $authorizeMintingResponse")

		this.authorizeMintingResponse = authorizeMintingResponse
	}

	private suspend fun continueWhenTransactionFinished(txHash: String): EthGetTransactionReceipt {
		while (true) {
			delay(5.seconds)
			Timber.d("checking if transaction finsihed")
			val receipt = getTransactionReceipt(txHash)
			if (receipt.transactionReceipt.isPresent) {
				val transactionReceipt = receipt.transactionReceipt.get()
				val success = transactionReceipt.status == "0x1"
				if (!success) {
					throw Exception("Couldn't get receipt")
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
