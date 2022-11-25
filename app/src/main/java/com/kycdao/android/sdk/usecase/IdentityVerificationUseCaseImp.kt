package com.kycdao.android.sdk.usecase

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
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
		resultContinuation: Continuation<InquiryResponse>
	) {

		startPersona(activity, templateId, referenceID, environment) { result ->
			resultContinuation.resume(result)
		}
	}

	private fun startPersona(
		activity: ComponentActivity,
		templateId: String,
		referenceId: String,
		environment: Environment,
		callback: ActivityResultCallback<InquiryResponse>
	) {
		Timber.d("startPersona(templateId: $templateId, referenceId: $referenceId)")

		val getInquiryResult =
			activity.activityResultRegistry.register(
				"IdentityVerificationUseCaseImp",
				Inquiry.Contract(),
				callback
			)

		val inquiry = Inquiry.fromTemplate(templateId)
			.referenceId(referenceId)
			.environment(environment = environment)
			.build()

		getInquiryResult.launch(inquiry)
	}

}