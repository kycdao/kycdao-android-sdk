package com.kycdao.android.sdk.network.api

data class UpdateUserRequestBody(
    val email: String,
    val residency: String,
    val legal_entity: Boolean,
)
