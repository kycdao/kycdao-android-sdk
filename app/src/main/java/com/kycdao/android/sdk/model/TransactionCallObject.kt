package com.kycdao.android.sdk.model

data class TransactionCallObject(
	val from: String,
	val to: String,
	val gas: String? = null,
	val gasPrice: String? = null,
	val value: String? = null,
	val data: String,
)