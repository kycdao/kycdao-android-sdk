package spoti.hu.kyctestapp.manager

import com.kycdao.android.sdk.verificationSession.VerificationSession
import com.kycdao.android.sdk.wallet.WalletConnectSession
import kotlinx.coroutines.flow.MutableStateFlow

interface SDKManager {
    val myWalletSession: MutableStateFlow<WalletConnectSession?>
    val myKycSessions: MutableList<VerificationSession>
}