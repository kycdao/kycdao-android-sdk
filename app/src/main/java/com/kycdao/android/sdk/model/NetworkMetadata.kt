package com.kycdao.android.sdk.model

import java.math.BigInteger


data class Network(
	val blockchain: String,
	val caip2id: String,
	val chain_id: Int?,
	val explorer: Explorer,
	val id: String,
	val name: String,
	val native_currency: NativeCurrency,
	val rpcUrl: String? = null
)

data class Explorer(
	val name: String,
	val transaction_path: String,
	val url: String,
)

data class NativeCurrency(
	val decimals: Int,
	val name: String,
	val symbol: String,
) {
	val baseToNativeDivisor: BigInteger = BigInteger("10").pow(decimals)
}
