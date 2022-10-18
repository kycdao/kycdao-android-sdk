package com.kycdao.android.sdk


import com.kycdao.android.sdk.usecase.IdentityVerificationUseCase
import com.kycdao.android.sdk.usecase.IdentityVerificationUseCaseImp
import org.koin.dsl.module

val domainModule = module {

    factory<IdentityVerificationUseCase> {
        IdentityVerificationUseCaseImp()
    }

}