package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.model.KycSession
import com.kycdao.android.sdk.model.KycUser
import kotlinx.coroutines.Job

interface PollIdentityVerificationRequestUseCase {
    operator fun invoke(kycSession: KycSession) : Job
}