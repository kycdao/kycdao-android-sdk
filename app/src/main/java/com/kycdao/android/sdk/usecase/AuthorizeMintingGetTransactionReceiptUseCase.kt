package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.model.KycSession

interface AuthorizeMintingGetTransactionReceiptUseCase {
    suspend operator fun invoke(kycSession: KycSession)
}