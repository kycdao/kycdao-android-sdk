package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.model.KycSession

interface AuthorizeMintingUseCase {
    suspend operator fun invoke(selectedNftId: String, kycSession: KycSession)
}