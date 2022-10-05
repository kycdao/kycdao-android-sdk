package com.kycdao.android.sdk.usecase

interface AuthorizeMintingUseCase {
    suspend operator fun invoke(selectedNftId: String)
}