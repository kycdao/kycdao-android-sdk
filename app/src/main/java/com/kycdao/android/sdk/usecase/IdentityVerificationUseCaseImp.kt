package com.kycdao.android.sdk.usecase

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import com.kycdao.android.sdk.model.PersonaSessionData
import com.withpersona.sdk2.inquiry.Environment
import com.withpersona.sdk2.inquiry.Inquiry
import com.withpersona.sdk2.inquiry.InquiryResponse
import timber.log.Timber
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

class IdentityVerificationUseCaseImp() : IdentityVerificationUseCase {

	override fun invoke(
		templateId: String,
		referenceID: String,
		environment: Environment,
		activity: ComponentActivity,
		resultContinuation: Continuation<InquiryResponse>,
		personaSessionData: PersonaSessionData?
	) {

		startPersona(activity, templateId, referenceID, environment,personaSessionData) { result ->
			resultContinuation.resume(result)
		}
	}

	private fun startPersona(
		activity: ComponentActivity,
		templateId: String,
		referenceId: String,
		environment: Environment,
		personaSessionData: PersonaSessionData?,
		callback: ActivityResultCallback<InquiryResponse>
	) {
		Timber.d("startPersona(templateId: $templateId, referenceId: $referenceId)")

		val getInquiryResult =
			activity.activityResultRegistry.register(
				"IdentityVerificationUseCaseImp",
				Inquiry.Contract(),
				callback
			)

		val inquiry = if(personaSessionData!=null) {
			Inquiry.fromInquiry(personaSessionData.inquiryId).sessionToken(personaSessionData.sessionToken).build()
		}else {
			Inquiry.fromTemplate(templateId)
				.referenceId(referenceId)
				.environment(environment = environment)
				.build()
		}

		getInquiryResult.launch(inquiry)
	}

}