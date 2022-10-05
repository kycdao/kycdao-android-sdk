package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.KycSession
import com.kycdao.android.sdk.model.KycUser

data class SessionDto(
    val id: String,
    val nonce: String,
    val user: UserDto? = null
) {
    fun mapToKycSession() : KycSession {
        return KycSession(
            walletConnected = true,
            nonce = nonce,
            kycUser = user?.mapToKycUser() ?: KycUser()
        )
    }
}
