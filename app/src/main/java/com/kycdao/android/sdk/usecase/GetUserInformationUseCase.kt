package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.model.KycUser

interface GetUserInformationUseCase {
    suspend operator fun invoke(kycUser: KycUser) : Boolean
}