package com.kycdao.android.sdk.model

import com.kycdao.android.sdk.util.decimalText
import com.kycdao.android.sdk.util.toText
import java.math.BigInteger

data class PriceEstimation(
	val paymentAmount: BigInteger,
	val gasFee: BigInteger?,
	val currency: NativeCurrency
) {
	val fullPrice : BigInteger
		get() = paymentAmount + (gasFee ?: BigInteger.ZERO)

	val paymentAmountText : String
		get() = paymentAmount.toText(currency)

	val gasFeeText: String?
		get(){
			if(gasFee== null) return null
			return gasFee.toText(currency)
		}
	val finalPriceText : String
		get() = fullPrice.toText(currency)

}