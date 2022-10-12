package com.kycdao.android.sdk


import com.kycdao.android.sdk.usecase.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {

    factory<UpdateUserUseCase> {
        UpdateUserUseCaseImp(get(),)
    }

    factory<PollEmailConfirmedUseCase> {
        PollEmailConfirmedUseCaseImp(get(),)
    }

    factory<PollIdentityVerificationRequestUseCase> {
        PollIdentityVerificationRequestUseCaseImp(get())
    }

    factory<IdentityVerificationUseCase> {
        IdentityVerificationUseCaseImp()
    }

}