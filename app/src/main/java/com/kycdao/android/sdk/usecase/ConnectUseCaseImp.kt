package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.wcsession._WCSession
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ConnectUseCaseImp(
    val createWCSessionUseCase : CreateWCSessionUseCase,
    val localDataSource: LocalDataSource,
    val walletIntent : WalletIntent,
) : ConnectUseCase  {

    override suspend fun invoke() : _WCSession = suspendCoroutine { continuation ->
        val wcSession = createWCSessionUseCase()
        wcSession.approvedCallback = {
            Timber.d( "wallet connect approved")
            wcSession.approvedCallback = null
            localDataSource.saveKycSession(localDataSource.getKycSession().copy(walletConnected = true))
            continuation.resume(wcSession)
        }
        walletIntent(wcSession.config.toWCUri())
    }
}