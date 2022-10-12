package com.kycdao.android.sdk.model

import org.web3j.utils.Convert
import java.math.BigInteger

data class GasPriceEstimation(
    val amount: BigInteger,
    val price: BigInteger,
    val currency: NativeCurrency // TODO unused implement it
){
    companion object {
        private val MIN_GAS_PRICE: BigInteger = Convert.toWei("50", Convert.Unit.GWEI).toBigInteger()
    }
    val finalGasPrice = price.max(MIN_GAS_PRICE)

    val fee get()= finalGasPrice * amount
}