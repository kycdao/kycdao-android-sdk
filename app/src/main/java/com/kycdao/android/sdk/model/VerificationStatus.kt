package com.kycdao.android.sdk.model

enum class VerificationStatus {
	VERIFIED, PROCESSING, NOT_VERIFIED
}


enum class VerificationStatusDto {
	Created, Failed, InReview, Verified, NotVerified;

	fun mapToModel(): VerificationStatus {
		return when (this) {
			Verified -> VerificationStatus.VERIFIED
			InReview -> VerificationStatus.PROCESSING
			Failed, NotVerified, Created -> VerificationStatus.NOT_VERIFIED
		}
	}
}