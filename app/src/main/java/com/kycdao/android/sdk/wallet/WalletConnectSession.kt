package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.model.SmartContractConfig
import com.kycdao.android.sdk.model.TransactionCallObject
import com.kycdao.android.sdk.model.WalletConnectURL
import com.kycdao.android.sdk.model.functions.mint.MintingProperties
import com.kycdao.android.sdk.model.functions.mint.MintingTransactionResult
import com.kycdao.android.sdk.model.functions.token_validation.HasValidTokenFunction
import com.kycdao.android.sdk.util.Resource
import com.kycdao.android.sdk.walletconnect.Session
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import org.komputing.khex.extensions.toHexString
import org.web3j.abi.FunctionEncoder
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class WalletConnectSession(
	val wcSession: Session,
	val wcConfig: Session.Config,
) : WalletSession {
	val url = WalletConnectURL(
		topic = wcConfig.handshakeTopic,
		bridgeURL = wcConfig.bridge ?: throw Exception("Missing bridge"),
		key = wcConfig.key ?: throw Exception("Missing key"),
		absoluteUri = wcConfig.toWCUri()
	)

	fun statusCallbackFlow() : Flow<Session.Status>  = callbackFlow {
		val callback = object: Session.Callback {
			override fun onStatus(status: Session.Status) {
				trySend(status)
				Timber.d("STATUS IS $status")
			}
			override fun onMethodCall(call: Session.MethodCall) {}
		}
		wcSession.addCallback(callback)
		awaitClose {
			removeListener()
		}
	}

	fun removeListener(){
		wcSession.clearCallbacks()
		wcSession.kill()
	}

	override val id: String = url.absoluteUri
 	val accounts get() = wcSession.approvedAccounts()
	val icons get() = wcSession.peerMeta()?.icons
	val name get() = wcSession.peerMeta()?.name

	fun getAvailableWallets(): List<String>? {
		return wcSession.approvedAccounts()
	}

	override fun getChainId(): String {
		val chainId = "eip155:${wcSession.chainId()}"
		Timber.d("chainID in CAIP-2 form: $chainId")
		return chainId
	}

	override suspend fun personalSign(walletAddress: String, message: String): String =
		suspendCoroutine { continuation ->
			val messageHex = message.toByteArray(Charsets.UTF_8).toHexString()
			Timber.d("---------- Input ----------")
			Timber.d("walletAddress: $walletAddress")
			Timber.d("message: $message")
			Timber.d("messageHex: $messageHex")
			wcSession.performMethodCall(
				Session.MethodCall.PersonalSignMessage(
					id = System.currentTimeMillis(),
					address = walletAddress,
					message = message
				)
			) {
				val signature = it.result as? String
				signature?.let {
					Timber.d("---------- Output ----------")
					Timber.d("signature: $signature")
					continuation.resume(signature)
				}
			}

			WalletIntent.executeFromUri(wcConfig.toWCUri())
		}

	override suspend fun sendMintingTransaction(
		walletAddress: String,
		mintingProperties: MintingProperties
	): MintingTransactionResult = suspendCoroutine { continuation ->
		wcSession.performMethodCall(
			Session.MethodCall.SendTransaction(
				id = System.currentTimeMillis(),
				from = walletAddress,
				to = mintingProperties.contractAddress,
				nonce = null,
				gasPrice = mintingProperties.gasPrice,
				gasLimit = mintingProperties.gasAmount,
				value = mintingProperties.paymentAmount,
				data = mintingProperties.contractABI,
			),
		) {
			Timber.d("Minting result from the wallet)")
			Timber.d("result:${it.result}")
			Timber.d("error:${it.error}")
			val transactionId = it.result as? String
			transactionId?.let {
				Timber.d("---------- Output ----------")
				Timber.d("transactionId: $transactionId")
				continuation.resume(MintingTransactionResult(transactionId))
			}
		}
		Timber.d("Open wallet for minting")
		WalletIntent.executeFromUri(wcConfig.toWCUri())
	}

}