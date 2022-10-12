package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.CustomKoinComponent
import com.kycdao.android.sdk.server.BridgeServer
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.komputing.khex.extensions.toNoPrefixHexString
import com.kycdao.android.sdk.walletconnect.Session
import com.kycdao.android.sdk.walletconnect.impls.MoshiPayloadAdapter
import com.kycdao.android.sdk.walletconnect.impls.OkHttpTransport
import com.kycdao.android.sdk.walletconnect.impls.WCSession
import com.kycdao.android.sdk.walletconnect.impls.WCSessionStore
import timber.log.Timber
import java.util.*

/**
 * A helper object responsible for establishing the connection to a wallet via WalletConnect
 *
 *
 *
 *
 */
object WalletConnectManager : CustomKoinComponent() {
    private var wcSession : WalletSessionDefaultImpl? = null

    private val moshi : Moshi by inject()
    private val storage : WCSessionStore by inject()
    private val client : OkHttpClient by inject(qualifier = named("WalletConnectClient"))
    private val bridge : BridgeServer by inject()
    init {
        bridge.start()
    }

    private fun createWCSession() : WalletSessionDefaultImpl{
        Timber.d( "create wallet connect session")
        wcSession?.wcSession?.clearCallbacks()
        val key = ByteArray(32).also { Random().nextBytes(it) }.toNoPrefixHexString()
        val config = Session.Config(
            UUID.randomUUID().toString(),
            "https://bridge.walletconnect.org",
            key)
        val session = WCSession(
            config,
            MoshiPayloadAdapter(moshi),
            storage,
            OkHttpTransport.Builder(client, moshi),
            Session.PeerMeta(url="https://staging.kycdao.xyz/",name = "KYCDAO",description = "A multichain platform for issuing reusable, onchain KYC verifications",icons = arrayListOf())
        ).also {
            it.offer()
        }
        val wcSession = WalletSessionDefaultImpl(session, config)
        Timber.d( "---------- Output ----------")
        Timber.d( "wcSession: $wcSession")
        return wcSession
    }

    /**
     * Gets the uri needed to show Qr code
     *
     * @return WalletConnect Uri
     */
    fun getUri() : String{
        val wcSessionNotNull = wcSession ?: throw Exception("No wallet connect session avalaible")
        return wcSessionNotNull.wcConfig.toWCUri()
    }

    /**
     * Connects to a wallet via WalletConnect
     *
     * @param onConnectionEstablished A callback function called when a connection was successfully established between the wallet and the client.
     */
    fun connectWallet(onConnectionEstablished : (WalletSession)->Unit){
        wcSession = createWCSession()
        wcSession?.let {session ->
            WalletIntent.executeFromUri(session.wcConfig.toWCUri())
        }
        wcSession?.addListenerOnEstablished { walletSession ->
            Timber.d( "wallet connect approved")
            onConnectionEstablished(walletSession)
        }
    }
}