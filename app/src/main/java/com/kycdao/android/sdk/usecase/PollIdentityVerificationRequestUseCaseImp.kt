package com.kycdao.android.sdk.usecase


import com.kycdao.android.sdk.kycSession.KycSession
import com.kycdao.android.sdk.network.NetworkDatasource
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class PollIdentityVerificationRequestUseCaseImp(
    private val networkDatasource: NetworkDatasource,
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : PollIdentityVerificationRequestUseCase {

    companion object {
        private val REPEAT_TIME = TimeUnit.SECONDS.toMillis(10)
    }

    override fun invoke(kycSession: KycSession) : Job {
        return ioScope.launch {
            while (true) {
                if (isVerified(kycSession)) {
                    Timber.d("user verified")
                    break
                } else {
                    Timber.d("user not verified, retry in $REPEAT_TIME ms")
                    delay(REPEAT_TIME)
                }
            }
        }
    }

    private suspend fun isVerified(kycSession: KycSession) : Boolean {
        Timber.d("user verification check -> GET /user")
        val networkKycUser = networkDatasource.getUser().mapToKycUser()

        if (networkKycUser.isIdentityVerified()) {
            kycSession.sessionData.user = networkKycUser
            return true
        }
        return false
    }
}