package com.kycdao.android.sdk.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.model.KycSession
import kotlinx.coroutines.delay
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt
import timber.log.Timber
import java.util.concurrent.TimeUnit

class AuthorizeMintingGetTransactionReceiptUseCaseImp(
    private val localDataSource: LocalDataSource,
    private val web3j: Web3j,
) : AuthorizeMintingGetTransactionReceiptUseCase {

    companion object {
        private val REPEAT_TIME = TimeUnit.SECONDS.toMillis(10)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun invoke(kycSession: KycSession) {
        val transactionHash = kycSession.authorizeMintingResponse?.tx_hash
        if (transactionHash != null) {
            val result = pollGetTransactionReceipt(transactionHash)
            saveResult(result)
        } else {
            Timber.d("error: transaction hash missing")
            // error transaction hash missing
        }
    }

    private fun saveResult(result: Boolean) {
        localDataSource.saveKycSession(localDataSource.getKycSession().copy(
            authorizeMintingFinished = true,
        ))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private suspend fun pollGetTransactionReceipt(
        transactionHash: String,
    ) : Boolean {
        Timber.d("start pollGetTransactionReceipt")
        while (true) {
            val ethGetTransactionReceipt = getTransactionReceipt(transactionHash)
            Timber.d("transactionReceipt: ${ethGetTransactionReceipt.transactionReceipt}")
            Timber.d("error: ${ethGetTransactionReceipt.error}")
            Timber.d("isPresent: ${ethGetTransactionReceipt.transactionReceipt.isPresent}")
            if (ethGetTransactionReceipt.transactionReceipt.isPresent) {
                val transactionReceipt = ethGetTransactionReceipt.transactionReceipt.get()
                val success = transactionReceipt.status == "0x1"
                if(!success) {
                    throw Exception("Couldn't get receipt")
                }
                return success
            }
            Timber.d("no result, retry in $REPEAT_TIME ms")
            delay(REPEAT_TIME)
        }
    }

    private fun getTransactionReceipt(
        transactionHash: String,
    ): EthGetTransactionReceipt {
        return web3j.ethGetTransactionReceipt(transactionHash).sendAsync().get()
    }


}