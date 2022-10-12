package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.kycSession.KycSession

interface UpdateUserUseCase {
    suspend operator fun invoke(kycSession: KycSession)
}