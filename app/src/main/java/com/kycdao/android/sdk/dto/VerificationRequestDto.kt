package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.VerificationRequest
import com.kycdao.android.sdk.model.VerificationStatusDto
import com.kycdao.android.sdk.model.VerificationType

data class VerificationRequestDto(
	val verification_type: VerificationType,
	val status: VerificationStatusDto
) {
	fun mapToVerificationRequest(): VerificationRequest {
		return VerificationRequest(
			verificationType = verification_type,
			status = status.mapToModel()
		)
	}
}
