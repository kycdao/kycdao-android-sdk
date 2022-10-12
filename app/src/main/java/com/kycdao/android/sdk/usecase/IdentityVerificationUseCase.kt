package com.kycdao.android.sdk.usecase

import androidx.activity.ComponentActivity
import com.kycdao.android.sdk.kycSession.KycSession
import com.withpersona.sdk2.inquiry.InquiryResponse

interface IdentityVerificationUseCase {
    operator fun invoke(kycSession: KycSession, activity: ComponentActivity, onCompleted : ((InquiryResponse.Complete)->Unit)? =null)
}