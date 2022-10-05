package com.kycdao.android.sdk.usecase

interface NftSelectionUseCase {
    suspend operator fun invoke() : String?
}