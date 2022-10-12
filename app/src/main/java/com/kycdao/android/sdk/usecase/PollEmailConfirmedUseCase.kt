package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.kycSession.KycUser
import kotlinx.coroutines.Job

interface PollEmailConfirmedUseCase {
    operator fun invoke(kycUser: KycUser) : Job
}