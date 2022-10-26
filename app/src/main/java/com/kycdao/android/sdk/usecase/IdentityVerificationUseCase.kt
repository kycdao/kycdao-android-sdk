package com.kycdao.android.sdk.usecase

import androidx.activity.ComponentActivity
import com.withpersona.sdk2.inquiry.InquiryResponse
import kotlin.coroutines.Continuation

interface IdentityVerificationUseCase {
	operator fun invoke(
		templateId: String,
		referenceID: String,
		activity: ComponentActivity,
		resultContinuation: Continuation<InquiryResponse>
	)
}