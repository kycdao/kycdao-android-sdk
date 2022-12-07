package com.kycdao.android.sdk.model

import com.kycdao.android.sdk.verificationSession.User

data class SessionData(
    val id: String,
    val nonce: String,
    var user: User,
    val discountYears: Int?
)


