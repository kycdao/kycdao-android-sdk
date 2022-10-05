package com.kycdao.android.sdk.usecase

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultRegistry
import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.model.KycSession
import com.withpersona.sdk2.inquiry.Environment
import com.withpersona.sdk2.inquiry.Inquiry
import com.withpersona.sdk2.inquiry.InquiryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class IdentityVerificationUseCaseImp(
    private val localDataSource: LocalDataSource,
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : IdentityVerificationUseCase {

    override fun invoke(
        kycSession: KycSession,
        activity: ComponentActivity,
        onCompleted: ((InquiryResponse.Complete) -> Unit)?
    ) {

        val personaTemplateID = kycSession.personaData.templateID
        val referenceId = kycSession.sessionData.user.extId

        startPersona(activity, personaTemplateID, referenceId) { result ->
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