package com.kycdao.android.sdk.db

import org.koin.dsl.module

val dbModule = module {

    single<LocalDataSource> {
        LocalDataSourceImp()
    }

}