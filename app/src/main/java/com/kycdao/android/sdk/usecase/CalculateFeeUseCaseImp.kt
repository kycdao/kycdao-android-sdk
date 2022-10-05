package com.kycdao.android.sdk.usecase

import org.web3j.utils.Convert
import timber.log.Timber
import java.math.BigInteger

class CalculateFeeUseCaseImp(
    val estimateGasUseCase: EstimateGasUseCase,
    val ethGasPriceUseCase: EthGasPriceUseCase
) : CalculateFeeUseCase {

    companion object {
        public val MIN_GAS_PRICE: BigInteger = Convert.toWei("50", Convert.Unit.GWEI).toBigInteger()
    }

    override suspend fun invoke(): BigInteger {
        val gas = estimateGasUseCase()
        val gasPrice = ethGasPriceUseCase()
        val finalGasPrice = gasPrice.max(MIN_GAS_PRICE)

        val fee = finalGasPrice * gas
        val matic = Convert.fromWei(fee.toBigDecimal(), Convert.Unit.ETHER)

        Timber.d( "---------- Input ----------")
        Timber.d( "MIN_GAS_PRICE: $MIN_GAS_PRICE")
        Timber.d( "gasPrice: $gasPrice")
        Timber.d( "gas: $gas")
        Timber.d( "---------- Output ----------")
        Timber.d( "finalGasPrice: $finalGasPrice")
        Timber.d( "fee: $fee wei")
        Timber.d( "fee: $matic MATIC")

        return fee
    }

}