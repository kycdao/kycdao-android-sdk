package com.kycdao.android.sdk.usecase

import android.content.Context
import android.widget.Toast
import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.model.State
import com.kycdao.android.sdk.ui.progress_screen.ProgressManager
import com.kycdao.android.sdk.ui.progress_screen.model.Action
import com.kycdao.android.sdk.ui.progress_screen.model.Progress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class TestUseCaseImp(
    private val context: Context,
    private val localDataSource: LocalDataSource,
    private val createKycSessionUseCase: CreateKycSessionUseCase,
    private val personalSignUseCase: PersonalSignUseCase,
    private val loginUseCase: LoginUseCase,
    private val connectUseCase: ConnectUseCase,
    private val sendDisclaimerAcceptUseCase: SendDisclaimerAcceptUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val sendEmailConfirmUseCase: SendEmailConfirmUseCase,
    private val pollEmailConfirmedUseCase: PollEmailConfirmedUseCase,
    private val identityVerificationUseCase: IdentityVerificationUseCase,
    private val pollIdentityVerificationRequestUseCase: PollIdentityVerificationRequestUseCase,
    private val nftSelectionUseCase: NftSelectionUseCase,
    private val authorizeMintingUseCase: AuthorizeMintingUseCase,
    private val authorizeMintingGetTransactionReceiptUseCase: AuthorizeMintingGetTransactionReceiptUseCase,
    private val calculateFeeUseCase: CalculateFeeUseCase,
    private val mintingUseCase: MintingUseCase,
    private val mintingGetTransactionReceiptUseCase: MintingGetTransactionReceiptUseCase,
    private val sendMintTokenUseCase: SendMintTokenUseCase,
) : TestUseCase {

    override suspend fun invoke() {
        Timber.d( "start")
        nextStep()
    }

    private suspend fun nextStep() {
        val kycSession = localDataSource.getKycSession()
        val state = kycSession.getState()

        Timber.d( "step start: $state")
        Timber.d( "kycSession: $kycSession")

        when (state) {
            State.CONNECT_WALLET -> functionWithButton(state, "Open wallet") { connect() }
            State.SESSION_REQUIRED -> runFunction(state) { createSession() }
            State.LOGIN_REQUIRED -> functionWithButton(state, "Open wallet") { login() }
            State.USER_INFORMATION_REQUIRED -> functionWithButton(state, "Add information") { userInformation() }
            State.WAIT_EMAIL_CONFIRMED -> runFunctionWithButton(state, { pollEmailConfirmed() }, "Resend email") { sendEmailConfirm() }
            State.IDENTITY_VERIFICATION -> functionWithButton(state, "Start") { identityVerification() }
            State.POLL_IDENTITY_VERIFICATION_RESULT -> runFunction(state) { pollIdentityVerificationRequest() }
            State.NFT_IMAGE_SELECTION -> functionWithButton(state, "Open chooser") { selectNFT() }
            State.WAITING_FOR_MINTING_AUTHORISATION -> runFunction(state) { checkAuthorizeMinting() }
            State.CALCULATE_FEE -> runFunction(state) { calculateFee() }
            State.MINTING -> functionWithButton(state, "Open wallet") { minting() }
            State.CHECK_MINTING -> runFunction(state) { checkMinting() }
            State.POST_MINT_TOKEN_ID -> runFunction(state) { sendMintToken() }
            State.COMPLETED -> ProgressManager.setComplete(state)
        }
    }

    private suspend fun connect() {
        connectUseCase()
    }

    private suspend fun createSession() {
        val walletAddress = localDataSource.getWCSession().session.approvedAccounts()!!.first()
        val chainId = localDataSource.getWCSession().session.chainId()
        Timber.d("chainId: $chainId")
        createKycSessionUseCase(walletAddress)
    }

    private suspend fun login() {
        personalSignUseCase()
        loginUseCase()
    }

    private suspend fun userInformation() {
        val success = getUserInformationUseCase()
        if (success) {
            updateUserUseCase()

            if (!localDataSource.getKycSession().isDisclaimerAccepted()) {
                sendDisclaimerAcceptUseCase()
            }

            if (!localDataSource.getKycSession().isEmailConfirmed()) {
                sendEmailConfirm()
            }
        }
    }

    private suspend fun sendEmailConfirm() {
        sendEmailConfirmUseCase()
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context, "Confirmation email sent",Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun pollEmailConfirmed() {
        pollEmailConfirmedUseCase()
    }

    private suspend fun identityVerification() {
        identityVerificationUseCase()
    }

    private suspend fun pollIdentityVerificationRequest() {
        pollIdentityVerificationRequestUseCase()
    }

    private suspend fun selectNFT() {
        val selectedNftId = nftSelectionUseCase()
        Timber.d( "selectedNftId: $selectedNftId")
        selectedNftId?.let {
            authorizeMintingUseCase.invoke(it)
        }
    }

    private suspend fun checkAuthorizeMinting() {
        authorizeMintingGetTransactionReceiptUseCase()
    }

    private suspend fun calculateFee() {
        calculateFeeUseCase()
        localDataSource.saveKycSession(localDataSource.getKycSession().copy(feeCalculationFinished = true))
    }

    private suspend fun minting() {
        mintingUseCase()
    }

    private suspend fun checkMinting() {
        mintingGetTransactionReceiptUseCase()
    }

    private suspend fun sendMintToken() {
        sendMintTokenUseCase()
    }

    private suspend fun functionWithButton(state: State, label: String, function: suspend () -> Unit) {
        ProgressManager.get(state).apply {
            progress = Progress.TODO
            actions = listOf (Action(label, true) { runFunction(state, function) })
            show = true
        }
        ProgressManager.updateFlow()
    }

    private suspend fun runFunction(state: State, function: suspend () -> Unit) {
        ProgressManager.setInProgress(state)
        function()
        ProgressManager.get(state).apply {
            actions = emptyList()
        }
        ProgressManager.setComplete(state)
        nextStep()
    }

    private suspend fun runFunctionWithButton(
        state: State,
        autoStartFunction: suspend () -> Unit,
        buttonLabel: String,
        buttonFunction: suspend () -> Unit
    ) {
        ProgressManager.get(state).apply {
            progress = Progress.IN_PROGRESS
            actions = listOf (Action(buttonLabel, true) { runFunction(state, buttonFunction) })
            show = true
        }
        ProgressManager.updateFlow()

        autoStartFunction()
        ProgressManager.get(state).apply {
            actions = emptyList()
        }
        ProgressManager.setComplete(state)
        nextStep()
    }
}