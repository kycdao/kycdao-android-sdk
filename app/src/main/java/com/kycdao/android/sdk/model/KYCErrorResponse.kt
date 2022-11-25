package com.kycdao.android.sdk.model

data class KYCErrorResponse(
	val reference_id: String?,
	val status_code: Int?,
	val internal: Boolean?,
	val error_code: KYCErrorCode?,
)

enum class KYCErrorCode{
	DisclaimerAlreadyAccepted,
	EmailAlreadyConfirmed,
	SessionUserAlreadyExists,
	AlreadyAccepted
}