package com.kycdao.android.sdk.network.request_models

data class UpdateUserRequestBody(
    val email: String,
    val residency: String,
    val legal_entity: Boolean,
)
