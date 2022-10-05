package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.ExampleApplication
import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.wcsession._WCSession
import org.komputing.khex.extensions.toNoPrefixHexString
import org.walletconnect.Session
import org.walletconnect.impls.MoshiPayloadAdapter
import org.walletconnect.impls.OkHttpTransport
import org.walletconnect.impls.WCSession
import org.walletconnect.nullOnThrow
import timber.log.Timber
import java.util.*

class CreateWCSessionUseCaseImp(
    private val localDataSource: LocalDataSource
) : CreateWCSessionUseCase {

    override fun invoke(): _WCSession {
        Timber.d( "create wallet connect session")

        nullOnThrow { localDataSource.getWCSession().session }?.clearCallbacks()
        val key = ByteArray(32).also { Random().nextBytes(it) }.toNoPrefixHexString()
        val config = Session.Config(
            UUID.randomUUID().toString(),
            "https://bridge.walletconnect.org",
            key)
        val session = WCSession(
            config,
            MoshiPayloadAdapter(ExampleApplication.moshi),
            ExampleApplication.storage,
            OkHttpTransport.Builder(ExampleApplication.client, ExampleApplication.moshi),
            Session.PeerMeta(url="https://staging.kycdao.xyz/",name = "KYCDAO",description = "A multichain platform for issuing reusable, onchain KYC verifications",icons = arrayListOf())
        ).also {
            it.offer()
        }
        val wcSession = _WCSession(config, session)
        Timber.d( "---------- Output ----------")
        Timber.d( "wcSession: $wcSession")
        localDataSource.saveWCSession(wcSession)
        return wcSession
    }

}