package com.kycdao.android.sdk

import com.kycdao.android.sdk.ui.ProgressActivity
import com.kycdao.android.sdk.ui.nftselector.NftSelectorContract
import com.kycdao.android.sdk.ui.personaldataform.GetUserInformationContract
import com.kycdao.android.sdk.usecase.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {

    factory<CreateWCSessionUseCase> {
        CreateWCSessionUseCaseImp(get())
    }

    factory<WalletIntent> {
        WalletIntentImp(androidContext())
    }

    factory<ConnectUseCase> {
        ConnectUseCaseImp(get(), get(), get())
    }

    factory<CreateKycSessionUseCase> {
        CreateKycSessionUseCaseImp(get(), get())
    }

    factory<LoginUseCase> {
        LoginUseCaseImp(get(), get())
    }

    factory<PersonalSignUseCase> {
        PersonalSignUseCaseImp(get(), get())
    }

    factory<SendDisclaimerAcceptUseCase> {
        SendDisclaimerAcceptUseCaseImp(get(), get())
    }

    factory<UpdateUserUseCase> {
        UpdateUserUseCaseImp(get(), get())
    }

    factory<SendEmailConfirmUseCase> {
        SendEmailConfirmUseCaseImp(get())
    }

    factory<PollEmailConfirmedUseCase> {
        PollEmailConfirmedUseCaseImp(get(), get())
    }

    factory<PollIdentityVerificationRequestUseCase> {
        PollIdentityVerificationRequestUseCaseImp(get(), get())
    }

    factory<AuthorizeMintingUseCase> {
        AuthorizeMintingUseCaseImp(get(), get())
    }

    factory<AuthorizeMintingGetTransactionReceiptUseCase> {
        AuthorizeMintingGetTransactionReceiptUseCaseImp(get(), get())
    }

    factory<EstimateGasUseCase> {
        EstimateGasUseCaseImp(get(), get())
    }

    factory<EthGasPriceUseCase> {
        EthGasPriceUseCaseImp(get())
    }

    factory<CalculateFeeUseCase> {
        CalculateFeeUseCaseImp(get(), get())
    }

    factory<MintingUseCase> {
        MintingUseCaseImp(get(), get(), get(), get())
    }

    factory<MintingGetTransactionReceiptUseCase> {
        MintingGetTransactionReceiptUseCaseImp(get(), get())
    }

    factory<SendMintTokenUseCase> {
        SendMintTokenUseCaseImp(get(), get())
    }
    factory<IdentityVerificationUseCase> {
        IdentityVerificationUseCaseImp(
            localDataSource = get()
        )
    }
    factory<NftSelectionUseCase> {
        NftSelectionUseCaseImp(
            NftSelectorContract()
        )
    }
    scope<ProgressActivity> {
        factory<TestUseCase> {
            TestUseCaseImp(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(),
                get(), get(), get(), get(), get(), get(), get(), get(), get(), get())
        }

        factory<GetUserInformationUseCase> {
            GetUserInformationUseCaseImp(
                activityResultRegistry = get<ProgressActivity>().activityResultRegistry,
                get(),
                GetUserInformationContract()
            )
        }


    }

}