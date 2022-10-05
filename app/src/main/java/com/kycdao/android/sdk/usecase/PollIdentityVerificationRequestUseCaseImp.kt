package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.network.NetworkDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PollIdentityVerificationRequestUseCaseImp(
    private  val networkDatasource: NetworkDatasource,
    private  val localDataSource: LocalDataSource,
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : PollIdentityVerificationRequestUseCase {

    companion object {
        private val REPEAT_TIME = TimeUnit.SECONDS.toMillis(10)
    }

    override suspend fun invoke() : Unit = suspendCoroutine { continuation ->
        ioScope.launch {
            while (true) {
                if (isVerified()) {
                    Timber.d("user verified")
                    continuation.resume(Unit)
                    break
                } else {
                    Timber.d("user not verified, retry in $REPEAT_TIME ms")
                    delay(10000)
                }
            }
        }
    }

    private suspend fun isVerified() : Boolean {
        Timber.d("user verification check -> GET /user")
        val kycUser = networkDatasource.getUser().mapToKycUser()

        if (kycUser.isIdentityVerified()) {
            localDataSource.saveKycUser(kycUser)
            return true
        }
        return false
    }
}