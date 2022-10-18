package com.kycdao.android.sdk.usecase

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import com.withpersona.sdk2.inquiry.Environment
import com.withpersona.sdk2.inquiry.Inquiry
import com.withpersona.sdk2.inquiry.InquiryResponse
import timber.log.Timber

class IdentityVerificationUseCaseImp() : IdentityVerificationUseCase {

	override fun invoke(
		templateId: String,
		referenceID: String,
		activity: ComponentActivity,
		onCompleted: ((InquiryResponse.Complete) -> Unit)?
	) {

		startPersona(activity, templateId, referenceID) { result ->
			when (result) {
				is InquiryResponse.Complete -> {
					onCompleted?.invoke(result)
					// ... completed flow
					Timber.d("Complete")
				}
				is InquiryResponse.Cancel -> {
					// ... abandoned flow
					Timber.d("Cancel")
				}
				is InquiryResponse.Error -> {
					// ... something went wrong
					Timber.d("Error")
				}
			}
		}
	}

	private fun startPersona(
		activity: ComponentActivity,
		templateId: String,
		referenceId: String,
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
			.environment(Environment.SANDBOX)
			.build()

		getInquiryResult.launch(inquiry)
	}
}