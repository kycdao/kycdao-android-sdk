package com.kycdao.android.sdk.network.request_models

data class MintTokenBody(
    val authorization_code: String,
    val token_id: String,
    val minting_tx_id: String,
)
