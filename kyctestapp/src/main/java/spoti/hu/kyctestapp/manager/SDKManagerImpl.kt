package spoti.hu.kyctestapp.manager

import com.kycdao.android.sdk.verificationSession.VerificationSession
import com.kycdao.android.sdk.wallet.WalletConnectSession
import kotlinx.coroutines.flow.MutableStateFlow

class SDKManagerImpl : SDKManager {
    override var myWalletSession = MutableStateFlow<WalletConnectSession?>(null)
    override var myKycSessions: MutableList<VerificationSession> = mutableListOf()

}