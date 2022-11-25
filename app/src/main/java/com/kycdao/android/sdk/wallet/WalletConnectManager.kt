package com.kycdao.android.sdk.wallet

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.kycdao.android.sdk.CustomKoinComponent
import com.kycdao.android.sdk.exceptions.WalletSessionNotAvailableException
import com.kycdao.android.sdk.exceptions.WalletSessionNotListening
import com.kycdao.android.sdk.server.BridgeServer
import com.kycdao.android.sdk.util.Resource
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import kotlin.collections.HashMap
import kotlin.coroutines.CoroutineContext


typealias RPCURL = String
typealias ChainID = String

/**
 * A helper object responsible for establishing the connection to a wallet via WalletConnect
 *
 */
object WalletConnectManager : CustomKoinComponent(), CoroutineScope {
	private var wcSession: WalletConnectSession? = null
	private val moshi: Moshi by inject()
	private val storage: WCSessionStore by inject()
	private val client: OkHttpClient by inject(qualifier = named("WalletConnectClient"))
	private val bridge: BridgeServer by inject()
	private val datastore: DataStore<Preferences> by inject()

	private val _wcURI = MutableStateFlow<String?>(null)
	val wcURI = _wcURI.asStateFlow()


	private val customRPCUrls: HashMap<ChainID, RPCURL> = hashMapOf()
	var isListening = false
	private val _sessionsState =
		MutableSharedFlow<Resource<WalletConnectSession>>(extraBufferCapacity = 1)
	val sessionsState = _sessionsState.asSharedFlow().onEach {
		openNewConnection()
		if (it is Resource.Success) {
			it.data.rpcURL = customRPCUrls[it.data.getChainId()]
		}
	}
	val activeSession = sessionsState.map {
		when (it) {
			is Resource.Failure -> null
			is Resource.Success -> it
		}
	}.stateIn(this, SharingStarted.Eagerly, null)

	init {
		bridge.start()
	}

	private fun createWCSession(): WalletConnectSession {
		Timber.d("create wallet connect session")
		val key = ByteArray(32).also { Random().nextBytes(it) }.toNoPrefixHexString()

		val handshakeTopic = UUID.randomUUID().toString()
		val config = Session.Config(
			handshakeTopic,
			"https://bridge.walletconnect.org",
			key
		)
		val session = WCSession(
			config,
			MoshiPayloadAdapter(moshi),
			storage,
			OkHttpTransport.Builder(client, moshi),
			Session.PeerMeta(
				url = "https://staging.kycdao.xyz/",
				name = "KYCDAO",
				description = "A multichain platform for issuing reusable, onchain KYC verifications",
				icons = arrayListOf()
			)
		).also {
			it.offer()
		}
		val wcSession = WalletConnectSession(session, config)
		launch {
			datastore.saveWCKey(handshakeTopic)
		}
		Timber.d("---------- Output ----------")
		Timber.d("wcSession: $wcSession")
		return wcSession
	}

	fun stopListening() {
		wcSession?.removeListener()
		isListening = false
	}

	fun addCustomRPCUrl(id: ChainID, url: RPCURL) {
		customRPCUrls[id] = url
	}

	/**
	 * Sets a callback to run when a connection with a wallet is successfully established
	 *
	 * @param onConnectionEstablished A callback function to run when a connection was successfully established between the wallet and the client.
	 */
	fun startListening() {
		launch {
			if (!isListening) {
				checkOldConnection()
				openNewConnection()
				isListening = true
			}
		}
	}

	suspend fun checkOldConnection() {
		val oldKey = datastore.getOldWCKey()
		Timber.d("Oldkey: $oldKey")
		if (oldKey != null) {
			closeOldConnection(oldKey)
		}
	}

	private fun closeOldConnection(oldWCKey: String) {
		val key = ByteArray(32).also { Random().nextBytes(it) }.toNoPrefixHexString()
		val config = Session.Config(
			oldWCKey,
			"https://bridge.walletconnect.org",
			key
		)
		WCSession(
			config,
			MoshiPayloadAdapter(moshi),
			storage,
			OkHttpTransport.Builder(client, moshi),
			Session.PeerMeta(
				url = "https://staging.kycdao.xyz/",
				name = "KYCDAO",
				description = "A multichain platform for issuing reusable, onchain KYC verifications",
				icons = arrayListOf()
			)
		).also {
			it.kill()
			Timber.d("CLOSE CONNECTION OLD")
		}
		launch {
			datastore.deleteOldKey()
		}
	}

	private fun openNewConnection() {
		wcSession = createWCSession()
		wcSession?.let { session ->
			_wcURI.tryEmit(session.wcConfig.toWCUri())
			session.statusCallbackFlow().map {
				when (it) {
					Session.Status.Approved -> {
						Resource.Success(session)
					}
					Session.Status.Disconnected -> {
						Resource.Failure("Disconnected")
					}
					is Session.Status.Error -> {
						Resource.Failure(it.throwable.message ?: "Unknown error")
					}
					else -> {
						null
					}
				}
			}.filterNotNull().onEach {
				_sessionsState.tryEmit(it)
			}.launchIn(this)
			Timber.d("Listener added")
		}
	}

	/**
	 * Starts connection process to a wallet via WalletConnect
	 */
	fun connectWallet() {
		if (!isListening)
			throw WalletSessionNotListening()
		wcSession?.let { session ->
			WalletIntent.executeFromUri(session.wcConfig.toWCUri())
		} ?: run {
			throw WalletSessionNotAvailableException()
		}
	}

	override val coroutineContext: CoroutineContext
		get() = Dispatchers.IO
}