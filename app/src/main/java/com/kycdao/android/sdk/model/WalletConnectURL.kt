package com.kycdao.android.sdk.model

data class WalletConnectURL(
    val topic: String,
    val bridgeURL: String,
    val key : String,
    val version: Int = 1,
    val absoluteUri : String
)