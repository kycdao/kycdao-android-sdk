package com.kycdao.android.sdk.model

import com.kycdao.android.sdk.dto.UserDto

data class SessionData(
    val id: String,
    val nonce: String,
    var user: KycUser
)


