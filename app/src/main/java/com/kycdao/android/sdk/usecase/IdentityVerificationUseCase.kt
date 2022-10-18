package com.kycdao.android.sdk.usecase

import androidx.activity.ComponentActivity
import com.withpersona.sdk2.inquiry.InquiryResponse

interface IdentityVerificationUseCase {
	operator fun invoke(
		templateId: String,
		referenceID: String,
		activity: ComponentActivity,
		onCompleted: ((InquiryResponse.Complete) -> Unit)? = null
	)
}