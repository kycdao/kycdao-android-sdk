package com.kycdao.android.sdk.ui

import com.kycdao.android.sdk.ui.nftselector.NftSelectorViewModel
import com.kycdao.android.sdk.ui.personaldataform.PersonalFormViewModel
import com.kycdao.android.sdk.ui.progress_screen.ProgressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel {
        PersonalFormViewModel()
    }

    viewModel { parameters ->
        NftSelectorViewModel(parameters.get())
    }

    viewModel {
        ProgressViewModel()
    }

//    factory<KycActivityResultContract<Any?, PersonalDataResult>> {
//        GetUserInformationContract()
//    }
//
//    factory<KycActivityResultContract<String, String>> {
//        NftSelectorContract()
//    }

}