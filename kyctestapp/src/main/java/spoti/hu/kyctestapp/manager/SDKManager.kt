package spoti.hu.kyctestapp.manager

import com.kycdao.android.sdk.verificationSession.VerificationSession
import com.kycdao.android.sdk.wallet.WalletConnectSession

interface SDKManager {

    fun saveWalletConnectSession(session: WalletConnectSession)
    fun saveVerificationSession(session: VerificationSession)

    fun getWalletConnectSession(): WalletConnectSession
    fun getVerificationSession(): VerificationSession
}