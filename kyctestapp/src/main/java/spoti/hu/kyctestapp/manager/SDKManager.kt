package spoti.hu.kyctestapp.manager

import com.kycdao.android.sdk.verificationSession.VerificationSession
import com.kycdao.android.sdk.wallet.WalletConnectSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SDKManager {

    fun saveWalletConnectSession(session: WalletConnectSession)
    fun saveVerificationSession(session: VerificationSession)
    val connectedWalletID : Flow<String>
    fun getWalletConnectSession(): WalletConnectSession
    fun getVerificationSession(): VerificationSession
}