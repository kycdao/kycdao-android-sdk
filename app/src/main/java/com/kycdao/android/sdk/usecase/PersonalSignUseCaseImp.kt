package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import org.komputing.khex.extensions.toHexString
import org.walletconnect.Session
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PersonalSignUseCaseImp(
    private val localDataSource: LocalDataSource,
    private val walletIntent: WalletIntent
) : PersonalSignUseCase {

    override suspend fun invoke(): Unit = suspendCoroutine { continuation ->
        val wcSession = localDataSource.getWCSession()
        val walletAddress = wcSession.session.approvedAccounts()!!.first()
        val message = localDataSource.getKycSession().loginProof()
        val messageHex = message.toByteArray(Charsets.UTF_8).toHexString()


        Timber.d( "---------- Input ----------")
        Timber.d( "walletAddress: $walletAddress")
        Timber.d( "message: $message")
        Timber.d( "messageHex: $messageHex")

        wcSession.session.performMethodCall(
            Session.MethodCall.PersonalSignMessage(
                id = System.currentTimeMillis(),
                address = walletAddress,
                message = message
            ),
        ) {
            val signature = it.result as? String
            signature?.let {
                Timber.d( "---------- Output ----------")
                Timber.d( "signature: $signature")
                val kycSession = localDataSource.getKycSession()
                localDataSource.saveKycSession(kycSession.copy(signature = signature))
                continuation.resume(Unit)
            }
        }
        walletIntent(wcSession.config.toWCUri())
    }


}