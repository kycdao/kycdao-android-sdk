package com.kycdao.android.sdk.usecase

import androidx.activity.ComponentActivity
import com.kycdao.android.sdk.model.KycSession

interface NftSelectionUseCase {
    suspend operator fun invoke(kycSession: KycSession, activity: ComponentActivity) : String?
}