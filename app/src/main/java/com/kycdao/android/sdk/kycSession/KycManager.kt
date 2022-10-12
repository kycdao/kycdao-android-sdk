package com.kycdao.android.sdk.kycSession


import androidx.activity.ComponentActivity
import com.kycdao.android.sdk.dto.SmartContractConfigDto
import com.kycdao.android.sdk.dto.toModel
import com.kycdao.android.sdk.model.*
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.api.CreateSessionRequestBody
import com.kycdao.android.sdk.network.api.LoginRequestBody
import com.kycdao.android.sdk.network.api.MintTokenBody
import com.kycdao.android.sdk.ui.convertBigInteger
import com.kycdao.android.sdk.usecase.*
import com.kycdao.android.sdk.util.asHexString
import com.kycdao.android.sdk.util.seconds
import com.kycdao.android.sdk.wallet.WalletSession
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt
import timber.log.Timber
import java.math.BigInteger
import kotlin.Exception

/**
 * A class responsible for managing the kycSession
 */
class KycManager() : KoinComponent {
    private val networkDatasource: NetworkDatasource =
        KoinJavaComponent.get<NetworkDatasource>(NetworkDatasource::class.java)
    private val web3j: Web3j by inject()
    lateinit var kycSession: KycSession
    val updateUserUseCase: UpdateUserUseCase by inject()

    /**
     * Creates a kycSession that will be used by the KycManager
     *
     * Must be called first to ensure that KycManager has access to a session
     *
     * @param walletAddress The wallet address which will be linked to the kycSession
     * @param walletSession An implementation of the WalletSession interface used to access the wallet of the user
     *
     * @return The created kycSession
     */
    suspend fun createSession(walletAddress: String, walletSession: WalletSession): KycSession {
        val networks = fetchSupportedNetworks()
        val desiredNetwork = networks.find { network ->
            network.caip2id == walletSession.getChainId()
        } ?: throw Exception("Unsupported network")
        val createSessionRequestBody = CreateSessionRequestBody(
            address = walletAddress,
            blockchain = desiredNetwork.blockchain
        )
        val sessionData =
            networkDatasource.createSession(createSessionRequestBody).mapToSessionData()

        val status = networkDatasource.getStatus()
        var kycContractConfig: SmartContractConfigDto? = null
        status.smart_contracts_info[desiredNetwork.id]?.get(VerificationType.KYC)?.let { contract ->
            kycContractConfig = contract
        } ?: run {
            throw Exception("No kyc config found")
        }
        var accreditedContractConfig: SmartContractConfigDto? = null
        status.smart_contracts_info[desiredNetwork.id]?.get(VerificationType.AccreditedInvestor)
            ?.let { contract ->
                accreditedContractConfig = contract
            }
        kycSession = KycSession(
            walletAddress = walletAddress,
            network = desiredNetwork,
            kycConfig = kycContractConfig?.toModel(),
            accreditedConfig = accreditedContractConfig?.toModel(),
            sessionData = sessionData,
            personaData = status.persona.toModel()
        )
        return kycSession
    }

    /**
     * Logs in
     *
     * @param signature Personal signed signature created by WalletSession.personalSign()
     *
     * @return Returns whether the login was successful or not
     */
    suspend fun login(signature: String): Boolean {
        // TODO handle network error
        if (kycSession.sessionData.user.id == null) {
            Timber.d("---------- Input ----------")
            Timber.e("signature: $signature")
            val userDto = networkDatasource.login(LoginRequestBody(signature))
            Timber.d("---------- Output ----------")
            Timber.e("userDto: $userDto")
            kycSession.sessionData.user = userDto.mapToKycUser()
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
        kycSession.let { mySession ->
            mySession.sessionData.user.apply {
                email = personalDataResult.email
                residency = personalDataResult.residency
                isLegalEntity = personalDataResult.isLegalEntity
            }
            updateUserUseCase(mySession)
            if (!mySession.isDisclaimerAccepted()) {
                sendIsDisclaimerAccepted()
            }
            if (!mySession.isEmailConfirmed()) {
                sendConfirmationEmail()
            }
        }
        pollEmailConfirmed()
    }

    val pollEmailUseCase: PollEmailConfirmedUseCase by inject()
    val pollIdentityVerificationRequestUseCase: PollIdentityVerificationRequestUseCase by inject()
    val selectNftSelectionUseCase: NftSelectionUseCase by inject()
    private var emailPollJob: Job? = null
    private var verificationPollJob: Job? = null
    private fun pollEmailConfirmed() {
        emailPollJob?.cancel()
        emailPollJob = pollEmailUseCase(kycSession.sessionData.user)
    }

    private fun pollVerificationCompleted() {
        verificationPollJob?.cancel()
        verificationPollJob = pollIdentityVerificationRequestUseCase(kycSession)
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
    suspend fun selectAndPrepareForNFTMinting(activity: ComponentActivity): String? {
        val selected = selectNftSelectionUseCase(kycSession, activity)
        Timber.d("Selected Nft Image: $selected")
        if (selected != null) {
            authorizeMintingOfNFT(selected)
            checkAuthorizeMinting()
        }
        return selected
    }

    private fun getTransactionReceipt(txHash: String): EthGetTransactionReceipt {
        return web3j.ethGetTransactionReceipt(txHash).sendAsync().get()
    }
    suspend fun testSuspend(){
        while(true){
            Timber.d("Test suspend")
            delay(3.seconds)

        }
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

    /**
     * Performs a minting operation
     *
     * @param performTransaction Lambda function which contains the actual minting call, using the WalletSession interface
     */
    suspend fun mint(performTransaction: suspend (MintingProperties) -> MintingTransactionResult?) {
        val authCode =
            kycSession.authorizeMintingResponse?.code ?: throw Exception("No auth code found")
        val mintingFunction = MintFunction(authCode)
        val transactionProperties = createMintingPropertiesFor(mintingFunction)
        val result = performTransaction(transactionProperties) ?: throw Exception("Failed to mint")
        Timber.d("Receipt hash: ${result.txHash}")
        val receipt = continueWhenTransactionFinished(result.txHash)

        val tokenId = receipt.transactionReceipt.get().logs[0].topics[3]
        val decimalTokenId = tokenId.convertBigInteger().toString(10)
        tokenMinted(authCode, decimalTokenId, result.txHash)

        kycSession.authorizeMintingResponse = null
    }

    private suspend fun tokenMinted(authCode: String, tokenId: String, txHash: String) {
        val mintTokenBody = MintTokenBody(
            authorization_code = authCode,
            token_id = tokenId,
            minting_tx_id = txHash
        )
        networkDatasource.sendMintToken(mintTokenBody)
    }

    private fun createMintingPropertiesFor(function: Function): MintingProperties {
        val gasPrice = calculateGasPrice(function)
        val resolvedContractAddress = kycSession.kycConfig?.address ?: throw Exception()
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
            currency = kycSession.network.native_currency
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
            kycSession.walletAddress,
            null,
            null,
            null,
            kycSession.kycConfig?.address,
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

    val identityVerificationUseCase: IdentityVerificationUseCase by inject()

    /**
     * Starts the persona identification process.
     *
     * @param activity The activity that starts the new activity containing the persona process
     */
    fun startPersonaIdentification(activity: ComponentActivity) {
        identityVerificationUseCase(kycSession, activity) {
            pollVerificationCompleted()
            Timber.d("verification Finished")
        }
    }

    private suspend fun checkAuthorizeMinting() {
        val mintingTxHash = kycSession.authorizeMintingResponse?.tx_hash ?: throw Exception()
        continueWhenTransactionFinished(mintingTxHash)
    }

    val authorizeMintingUseCase: AuthorizeMintingUseCase by inject()
    private suspend fun authorizeMintingOfNFT(selectedNftId: String) {
        authorizeMintingUseCase(selectedNftId, kycSession)
    }

    private suspend fun fetchSupportedNetworks(): List<Network> {
        return networkDatasource.getSupportedNetworks()
    }

    private suspend fun sendConfirmationEmail() {
        Timber.d("Confirmation email sent")
        networkDatasource.sendEmailConfirm()
    }

    private suspend fun sendIsDisclaimerAccepted() {
        if (!kycSession.isDisclaimerAccepted()) {
            Timber.d("sending disclaimer acceptance")
            networkDatasource.saveDisclaimer()
        } else {
            Timber.d("disclaimer acceptance has already been sent before")
        }
    }
}