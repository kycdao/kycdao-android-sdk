package com.kycdao.android.sdk.network.request_models

data class CreateSessionRequestBody(
    val address: String,
    val blockchain: String = "Ethereum",
)
