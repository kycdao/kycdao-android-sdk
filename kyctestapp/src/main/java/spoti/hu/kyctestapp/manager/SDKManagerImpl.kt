package spoti.hu.kyctestapp.manager

import com.kycdao.android.sdk.verificationSession.VerificationSession
import com.kycdao.android.sdk.wallet.WalletConnectSession
import kotlinx.coroutines.flow.*

class SDKManagerImpl : SDKManager {
    private var myWalletSession = MutableStateFlow<WalletConnectSession?>(null)
    private var myKycSessions = MutableStateFlow<VerificationSession?>(null)
    override fun saveWalletConnectSession(session: WalletConnectSession) {
        myWalletSession.update { session }
    }

    override fun saveVerificationSession(session: VerificationSession) {
        myKycSessions.update { session }
    }

    override val connectedWalletID: Flow<String> = myWalletSession.map { it?.id ?: "Unknown" }

    override fun getWalletConnectSession(): WalletConnectSession {
        return myWalletSession.value
            ?: throw java.lang.RuntimeException("WALLET SESSION IS NOT SET!")
    }

    override fun getVerificationSession(): VerificationSession {
        return myKycSessions.value
            ?: throw java.lang.RuntimeException("KYC SESSION IS NOT SET!")
    }

}