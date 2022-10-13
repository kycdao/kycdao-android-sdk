package com.kycdao.android.sdk.kycSession

import androidx.activity.ComponentActivity
import com.kycdao.android.sdk.CustomKoinComponent
import com.kycdao.android.sdk.KoinContainer
import com.kycdao.android.sdk.KoinContainer.identityVerificationUseCase
import com.kycdao.android.sdk.KoinContainer.networkDatasource
import com.kycdao.android.sdk.KoinContainer.pollEmailUseCase
import com.kycdao.android.sdk.KoinContainer.pollIdentityVerificationRequestUseCase
import com.kycdao.android.sdk.KoinContainer.web3j
import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.model.*
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.api.AuthorizeMintingRequestBody
import com.kycdao.android.sdk.network.api.LoginRequestBody
import com.kycdao.android.sdk.network.api.MintTokenBody
import com.kycdao.android.sdk.util.asHexString
import com.kycdao.android.sdk.util.convertBigInteger
import com.kycdao.android.sdk.util.seconds
import com.kycdao.android.sdk.wallet.WalletSession
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.inject
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt
import timber.log.Timber
import java.math.BigInteger

data class KycSession(
    val walletAddress: String,
    val network: Network,
    val kycConfig: SmartContractConfig?,
    val accreditedConfig: SmartContractConfig?,
    val personaData : Persona,
    val sessionData: SessionData,
    val walletConnection: WalletSession,
    val walletConnected: Boolean = false,
    val signature: String? = null,
    val identityVerificationCompleted: Boolean = false,
    var authorizeMintingResponse: AuthorizeMintingResponse? = null,
    val authorizeMintingFinished: Boolean = false,
    val feeCalculationFinished: Boolean = false,
    val mintTransactionId: String? = null,
    val mintTokenId: String? = null,
    val mintTokenSent: Boolean = false,
) {


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

    fun loginProof(): String {
        return "kycDAO-login-${sessionData.nonce}"
    }

    fun isEmailConfirmed(): Boolean {
        return sessionData.user.isEmailConfirmed()
    }

    fun isDisclaimerAccepted(): Boolean {
        return sessionData.user.disclaimerAccepted?.isNotEmpty() ?: false
    }

    fun hasUserInfo(): Boolean {
        sessionData.user.let { user ->
            return !user.email.isNullOrBlank() && !user.residency.isNullOrBlank() && user.isLegalEntity != null
        }
    }
    /**
     * Logs in
     *
     * @param signature Personal signed signature created by WalletSession.personalSign()
     *
     * @return Returns whether the login was successful or not
     */
    suspend fun login(): Boolean {
        // TODO handle network error
        if (sessionData.user.id == null) {
            val signature = walletConnection.personalSign(walletAddress,loginProof())
            Timber.d("---------- Input ----------")
            Timber.e("signature: $signature")
            val userDto = networkDatasource.login(LoginRequestBody(signature))
            Timber.d("---------- Output ----------")
            Timber.e("userDto: $userDto")
            sessionData.user = userDto.mapToKycUser()
        }
        return true
    }

    /**
     * Save the personal information of the user and sends confirmation email if email is not yet confirmed.
     *
     * By calling this function the disclaimer is also accepted.
     * The function finishes when the email is confirmed
     *
     * @param personalDataResult The personal information to be saved wrapped in a PersonalDataResult class
     */
    suspend fun savePersonalInfo(personalDataResult: PersonalDataResult) {
            sessionData.user.apply {
                email = personalDataResult.email
                residency = personalDataResult.residency
                isLegalEntity = personalDataResult.isLegalEntity
            }
            KoinContainer.updateUserUseCase(this)
            if (isDisclaimerAccepted()) {
                sendIsDisclaimerAccepted()
            }
            if (isEmailConfirmed()) {
                sendConfirmationEmail()
            }
        pollEmailConfirmed()
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
    suspend fun prepareForMintingOfNFT(selectedImage: String) {
        Timber.d("Selected Nft Image: $selectedImage")
        authorizeMintingOfNFT(selectedImage)
        checkAuthorizeMinting()
    }

    /**
     * Performs a minting operation
     *
     * @param performTransaction Lambda function which contains the actual minting call, using the WalletSession interface
     */
    suspend fun mint() {
        val authCode =
            authorizeMintingResponse?.code ?: throw Exception("No auth code found")
        val mintingFunction = MintFunction(authCode)
        val transactionProperties = createMintingPropertiesFor(mintingFunction)
        val result = walletConnection.sendMintingTransaction(walletAddress,transactionProperties)
        Timber.d("Receipt hash: ${result.txHash}")
        val receipt = continueWhenTransactionFinished(result.txHash)

        val tokenId = receipt.transactionReceipt.get().logs[0].topics[3]
        val decimalTokenId = tokenId.convertBigInteger().toString(10)
        tokenMinted(authCode, decimalTokenId, result.txHash)

        this.authorizeMintingResponse = null
    }

    /**
     * Starts the persona identification process.
     *
     * @param activity The activity that starts the new activity containing the persona process
     */
    fun startPersonaIdentification(activity: ComponentActivity) {
        identityVerificationUseCase(this, activity) {
            pollVerificationCompleted()
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

    private suspend fun sendIsDisclaimerAccepted() {
        if (isDisclaimerAccepted()) {
            Timber.d("sending disclaimer acceptance")
            networkDatasource.saveDisclaimer()
        } else {
            Timber.d("disclaimer acceptance has already been sent before")
        }
    }
    private var emailPollJob: Job? = null
    private var verificationPollJob: Job? = null
    private fun pollEmailConfirmed() {
        emailPollJob?.cancel()
        emailPollJob = pollEmailUseCase(sessionData.user)
    }
    private fun pollVerificationCompleted() {
        verificationPollJob?.cancel()
        verificationPollJob = pollIdentityVerificationRequestUseCase(this)
    }
    private suspend fun authorizeMintingOfNFT(selectedNftId: String) {
        val blockchainAccount = sessionData.user.blockchainAccounts.first()

        Timber.d( "---------- Input ----------")
        Timber.d( "selectedNftId: $selectedNftId")
        Timber.d( "blockchainAccount: $blockchainAccount")

        val authorizeMintingResponse: AuthorizeMintingResponse = networkDatasource.authorizeMinting(
            AuthorizeMintingRequestBody(
                //network is currently baked in
                blockchain_account_id = blockchainAccount.id,
                selected_image_id = selectedNftId
            )
        )

        Timber.d( "---------- Output ----------")
        Timber.d( "authorizeMintingResponse: $authorizeMintingResponse")

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
