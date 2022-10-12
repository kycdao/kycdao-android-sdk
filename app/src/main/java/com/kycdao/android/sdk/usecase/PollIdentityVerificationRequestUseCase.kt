package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.kycSession.KycSession
import kotlinx.coroutines.Job

interface PollIdentityVerificationRequestUseCase {
    operator fun invoke(kycSession: KycSession) : Job
}