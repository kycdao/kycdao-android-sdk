package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.model.KycUser
import com.kycdao.android.sdk.network.NetworkDatasource
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PollEmailConfirmedUseCaseImp(
    private val networkDatasource: NetworkDatasource,
    private val localDataSource: LocalDataSource,
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : PollEmailConfirmedUseCase {

    companion object {
        private val REPEAT_TIME = TimeUnit.SECONDS.toMillis(10)
    }

    override fun invoke(kycUser: KycUser) : Job {
        return ioScope.launch {
            while (true) {
                if (isConfirmed(kycUser)) {
                    Timber.d("email confirmed")
                    break
                } else {
                    Timber.d("email not confirmed, retry in $REPEAT_TIME ms")
                    delay(REPEAT_TIME)
                }
            }
        }
    }

    private suspend fun isConfirmed(kycUser: KycUser): Boolean {
        Timber.d("email confirmation check -> GET /user")
        val networkKycUser = networkDatasource.getUser().mapToKycUser()

        if (networkKycUser.isEmailConfirmed()) {
            kycUser.emailConfirmed = networkKycUser.emailConfirmed
            return true
        }
        return false
    }
}