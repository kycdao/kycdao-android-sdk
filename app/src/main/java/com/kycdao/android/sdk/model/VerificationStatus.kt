package com.kycdao.android.sdk.model

enum class VerificationStatus {
	Verified, Processing, NotVerified
}


enum class VerificationStatusDto {
	Created, Failed, InReview, Verified, NotVerified;

	fun mapToModel(): VerificationStatus {
		return when (this) {
			Verified -> VerificationStatus.Verified
			Created, InReview -> VerificationStatus.Processing
			Failed, NotVerified -> VerificationStatus.NotVerified
		}
	}
}