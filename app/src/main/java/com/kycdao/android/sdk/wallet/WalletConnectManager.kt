package com.kycdao.android.sdk.wallet

import android.content.Context
import com.kycdao.android.sdk.ExampleApplication
import com.kycdao.android.sdk.wcsession._WCSession
import jnr.ffi.mapper.FunctionMapper
import org.komputing.khex.extensions.toNoPrefixHexString
import org.walletconnect.Session
import org.walletconnect.impls.MoshiPayloadAdapter
import org.walletconnect.impls.OkHttpTransport
import org.walletconnect.impls.WCSession
import org.walletconnect.nullOnThrow
import timber.log.Timber
import java.util.*
import kotlin.coroutines.resume

object WalletConnectManager {
    private var wcSession : WalletSessionWCImpl? = null


    private fun createWCSession() : WalletSessionWCImpl{
        Timber.d( "create wallet connect session")
        wcSession?.wcSession?.clearCallbacks()
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
        val wcSession = WalletSessionWCImpl(session, config)
        Timber.d( "---------- Output ----------")
        Timber.d( "wcSession: $wcSession")
        return wcSession
    }

    fun connectWallet(onConnectionEstablished : (WalletSession)->Unit){
        wcSession = createWCSession()
        val intent = WalletIntent()
        wcSession?.let {session ->
            intent.executeFromUri(session.wcConfig.toWCUri())
        }
        wcSession?.addListenerOnEstablished { walletSession ->
            Timber.d( "wallet connect approved")
            onConnectionEstablished(walletSession)
        }
    }
}