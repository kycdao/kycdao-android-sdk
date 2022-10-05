package com.kycdao.android.sdk.usecase

interface GetUserInformationUseCase {
    suspend operator fun invoke() : Boolean
}