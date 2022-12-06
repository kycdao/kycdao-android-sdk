package com.kycdao.android.sdk.model

import com.kycdao.android.sdk.util.toText
import java.math.BigInteger

data class PaymentEstimation(
	val paymentAmount: BigInteger,
	val discountYears: UInt,
	val currency: NativeCurrency
) {
	val paymentAmountText: String
		get() = paymentAmount.toText(currency)
}