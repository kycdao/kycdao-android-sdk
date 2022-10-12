package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.model.MintingProperties
import com.kycdao.android.sdk.model.MintingTransactionResult
import org.komputing.khex.extensions.toHexString
import org.walletconnect.Session
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private typealias FunctionWithSession = (WalletSession) -> Unit

class WalletSessionDefaultImpl(
    val wcSession: Session,
    val wcConfig: Session.Config
) : WalletSession {

    fun addListenerOnEstablished(approvedCallback: FunctionWithSession) {
        wcSession.addCallback(object : Session.Callback {
            override fun onStatus(status: Session.Status) {
                Timber.d("onStatus: $status")
                if (status == Session.Status.Approved) {
                    approvedCallback.invoke(this@WalletSessionDefaultImpl)
                }
            }

            override fun onMethodCall(call: Session.MethodCall) {
            }
        })
    }

    override fun getAvailableWallets(): List<String>? {
        return wcSession.approvedAccounts()
    }

    override fun getChainId(): String {
        val chainId = "eip155:${wcSession.chainId()}"
        Timber.d("chainID in CAIP-2 form: $chainId")
        return chainId
    }

    /**
     * Valami komment
     */
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
    ): MintingTransactionResult = suspendCoroutine {  continuation ->
        wcSession.performMethodCall(
            Session.MethodCall.SendTransaction(
                id = System.currentTimeMillis(),
                from = walletAddress,
                to = mintingProperties.contractAddress,
                nonce = null,
                gasPrice = mintingProperties.gasPrice,
                gasLimit = mintingProperties.gasAmount,
                value = null,
                data = mintingProperties.contractABI,
            ),
        ) {
            Timber.d( "Minting result from the wallet)")
            Timber.d( "result:${it.result}")
            Timber.d( "error:${it.error}")
            val transactionId = it.result as? String
            transactionId?.let {
                Timber.d( "---------- Output ----------")
                Timber.d( "transactionId: $transactionId")
                continuation.resume(MintingTransactionResult(transactionId))
            }
        }
        Timber.d( "Open wallet for minting")
        WalletIntent.executeFromUri(wcConfig.toWCUri())
    }
}