package com.kycdao.android.sdk.model

data class MintingProperties(
    val walletAddress: String,
    val contractAddress: String,
    val contractABI: String,
    val gasAmount: String,
    val gasPrice: String,
)
