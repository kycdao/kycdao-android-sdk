package com.kycdao.android.sdk.model


data class Network(
    val blockchain: String,
    val caip2id: String,
    val chain_id: Int?,
    val explorer: Explorer,
    val id: String,
    val name: String,
    val native_currency: NativeCurrency
)

data class Explorer(
    val name: String,
    val transaction_path: String,
    val url: String
)

data class NativeCurrency(
    val decimals: Int,
    val name: String,
    val symbol: String
)
