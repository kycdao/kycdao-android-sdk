package com.kycdao.android.sdk

import android.app.Application
import android.content.SharedPreferences
import com.kycdao.android.sdk.wallet.walletSessionKeyDataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single{
        getSharedPrefs(androidApplication())
    }
    single {
        androidContext().walletSessionKeyDataStore
    }

}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return  androidApplication.getSharedPreferences("default",  android.content.Context.MODE_PRIVATE)
}