package com.kycdao.android.sdk.usecase

import org.web3j.protocol.Web3j
import timber.log.Timber
import java.math.BigInteger

class EthGasPriceUseCaseImp(
    private val web3j: Web3j,
) : EthGasPriceUseCase {

    override suspend fun invoke(): BigInteger {
        val ethGasPrice = web3j.ethGasPrice().sendAsync().get()

        Timber.d( "---------- Output ----------")

        if (ethGasPrice.error != null) {
            Timber.d( "error.code: ${ethGasPrice.error?.code}")
            Timber.d( "error.data: ${ethGasPrice.error?.data}")
            Timber.d( "error.message: ${ethGasPrice.error?.message}")
            // TODO error
        }

        Timber.d( "gasPrice: ${ethGasPrice.gasPrice}")

        return ethGasPrice.gasPrice
    }
}