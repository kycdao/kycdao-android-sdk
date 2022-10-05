package com.kycdao.android.sdk.usecase

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultRegistry
import com.kycdao.android.sdk.db.LocalDataSource
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
    private val activityResultRegistry: ActivityResultRegistry,
    private val localDataSource: LocalDataSource,
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : IdentityVerificationUseCase {

    override suspend fun invoke() : Unit = suspendCoroutine { continuation ->
        ioScope.launch {
            val status = localDataSource.getStatus()
            val referenceId = localDataSource.getKycSession().kycUser.extId

            startPersona(status.persona.template_id, referenceId) { result ->
                when (result) {
                    is InquiryResponse.Complete -> {
                        localDataSource.saveKycSession(localDataSource.getKycSession().copy(
                            identityVerificationCompleted = true
                        ))
                        // ... completed flow
                        Timber.d( "Complete")
                    }
                    is InquiryResponse.Cancel -> {
                        // ... abandoned flow
                        Timber.d( "Cancel")
                    }
                    is InquiryResponse.Error -> {
                        // ... something went wrong
                        Timber.d( "Error")
                    }
                }
                continuation.resume(Unit)
            }
        }
    }

    private fun startPersona(templateId: String, referenceId: String, callback: ActivityResultCallback<InquiryResponse>) {
        Timber.d( "startPersona(templateId: $templateId, referenceId: $referenceId)")

        val getInquiryResult =
            activityResultRegistry.register("IdentityVerificationUseCaseImp", Inquiry.Contract(), callback)

        val inquiry = Inquiry.fromTemplate(templateId)
            .referenceId(referenceId)
            .environment(Environment.SANDBOX)
            .build()

        getInquiryResult.launch(inquiry)
    }
}