package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.VerificationRequest

data class VerificationRequestDto(
    val verification_type: String,
    val status: String
) {
    fun mapToVerificationRequest() : VerificationRequest {
        return VerificationRequest(
            verificationType = verification_type,
            status = status
        )
    }
}
