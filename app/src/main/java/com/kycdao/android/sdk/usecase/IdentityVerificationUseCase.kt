package com.kycdao.android.sdk.usecase

import androidx.activity.ComponentActivity
import com.kycdao.android.sdk.model.PersonaSessionData
import com.withpersona.sdk2.inquiry.Environment
import com.withpersona.sdk2.inquiry.InquiryResponse
import kotlin.coroutines.Continuation

interface IdentityVerificationUseCase {
	operator fun invoke(
		templateId: String,
		referenceID: String,
		environment: Environment,
		activity: ComponentActivity,
		resultContinuation: Continuation<InquiryResponse>,
		personaSessionData : PersonaSessionData? = null
	)
}