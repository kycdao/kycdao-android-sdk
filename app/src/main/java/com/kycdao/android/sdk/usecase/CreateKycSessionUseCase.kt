package com.kycdao.android.sdk.usecase

interface CreateKycSessionUseCase {
    suspend operator fun invoke(walletAddress : String)
}