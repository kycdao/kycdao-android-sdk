package com.kycdao.android.sdk.model

import com.kycdao.android.sdk.util.decimalText
import com.kycdao.android.sdk.util.toText
import org.web3j.utils.Convert
import java.math.BigInteger

data class GasEstimation private constructor(
	val amount: BigInteger,
	var price: BigInteger,
	val gasCurrency: NativeCurrency,
) {
	companion object Factory {
		private val MIN_GAS_PRICE: BigInteger =
			Convert.toWei("50", Convert.Unit.GWEI).toBigInteger()

		fun estimate(
			amount: BigInteger,
			price: BigInteger,
			gasCurrency: NativeCurrency
		): GasEstimation {
			return GasEstimation(
				amount = amount,
				price = price.max(MIN_GAS_PRICE),
				gasCurrency = gasCurrency
			)
		}
	}

	val fee get() = price * amount
	fun feeText(): String =
		fee.toText(currency = gasCurrency)
}