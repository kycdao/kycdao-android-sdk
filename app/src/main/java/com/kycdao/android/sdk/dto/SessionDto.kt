package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.verificationSession.User
import com.kycdao.android.sdk.model.SessionData

data class SessionDto(
    val id: String,
    val nonce: String,
    val user: UserDto? = null,
    val discount_years: Int?
) {
    fun mapToSessionData() : SessionData {
        return SessionData(
            id = id,
            nonce = nonce,
            user = user?.mapToKycUser() ?: User(),
            discountYears = discount_years
        )
    }
}
