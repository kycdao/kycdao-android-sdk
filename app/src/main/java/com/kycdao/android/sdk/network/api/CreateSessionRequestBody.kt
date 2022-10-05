package com.kycdao.android.sdk.network.api

data class CreateSessionRequestBody(
    val address: String,
    val blockchain: String = "Ethereum",
)
