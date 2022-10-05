package com.kycdao.android.sdk.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.ui.convertBigInteger
import kotlinx.coroutines.delay
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MintingGetTransactionReceiptUseCaseImp(
    private val localDataSource: LocalDataSource,
    private val web3j: Web3j,
) : MintingGetTransactionReceiptUseCase {

    companion object {
        private val REPEAT_TIME = TimeUnit.SECONDS.toMillis(10)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun invoke() {
        val transactionHash = localDataSource.getKycSession().mintTransactionId
        if (transactionHash != null) {
            Timber.d( "---------- Input ----------")
            Timber.d( "transactionHash: $transactionHash")
            val result = pollGetTransactionReceipt(transactionHash)
            Timber.d( "---------- Output ----------")
            Timber.d( "mint tokenId: $result")
            if (!result.isNullOrEmpty()) {
                saveResult(result)
            } else {
                // error
            }
        } else {
            Timber.d( "error: transaction hash missing")
            // error transaction hash missing
        }
    }

    private suspend fun saveResult(tokenId: String) {
        localDataSource.saveKycSession(localDataSource.getKycSession().copy(
            mintTokenId = tokenId,
        ))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private suspend fun pollGetTransactionReceipt(
        transactionHash: String,
    ) : String? {
        while (true) {
            val ethGetTransactionReceipt = getTransactionReceipt(transactionHash)
            Timber.d( "transactionReceipt: ${ethGetTransactionReceipt.transactionReceipt}")
            Timber.d( "error: ${ethGetTransactionReceipt.error}")
            Timber.d( "isPresent: ${ethGetTransactionReceipt.transactionReceipt.isPresent}")
            if (ethGetTransactionReceipt.transactionReceipt.isPresent) {
                val transactionReceipt = ethGetTransactionReceipt.transactionReceipt.get()
                val success = transactionReceipt.status == "0x1"
                // TODO error if status not 1
                if (success) {
                    Timber.d( "logs.size: ${transactionReceipt.logs.size}")
                    Timber.d( "logs: ${transactionReceipt.logs}")
                    val log = transactionReceipt.logs[0]
                    val tokenId = log.topics[3]
                    Timber.d( "tokenId: $tokenId")
                    val decimalTokenId = tokenId.convertBigInteger().toString(10)
                    Timber.d( "decimalTokenId: $decimalTokenId")
                    return decimalTokenId
                } else {
                    return null
                }
            }
            Timber.d( "no result, retry in $REPEAT_TIME ms")
            delay(REPEAT_TIME)
        }
    }

    private fun getTransactionReceipt(
        transactionHash: String,
    ): EthGetTransactionReceipt {
        return web3j.ethGetTransactionReceipt(transactionHash).sendAsync().get()
    }

}