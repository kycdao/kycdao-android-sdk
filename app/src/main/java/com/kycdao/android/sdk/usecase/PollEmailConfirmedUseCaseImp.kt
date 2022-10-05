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

class PollEmailConfirmedUseCaseImp(
    private  val networkDatasource: NetworkDatasource,
    private  val localDataSource: LocalDataSource,
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : PollEmailConfirmedUseCase {

    companion object {
        private val REPEAT_TIME = TimeUnit.SECONDS.toMillis(10)
    }

    override suspend fun invoke() : Unit = suspendCoroutine { continuation ->
        ioScope.launch {
            while (true) {
                if (isConfirmed()) {
                    Timber.d("email confirmed")
                    continuation.resume(Unit)
                    break
                } else {
                    Timber.d("email not confirmed, retry in $REPEAT_TIME ms")
                    delay(REPEAT_TIME)
                }
            }
        }
    }

    private suspend fun isConfirmed() : Boolean {
        Timber.d("email confirmation check -> GET /user")
        val kycUser = networkDatasource.getUser().mapToKycUser()

        if (kycUser.isEmailConfirmed()) {
            localDataSource.saveKycUser(kycUser)
            return true
        }
        return false
    }
}