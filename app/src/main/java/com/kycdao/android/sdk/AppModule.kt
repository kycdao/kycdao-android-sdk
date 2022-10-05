package com.kycdao.android.sdk

import android.app.Application
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    single{
        getSharedPrefs(androidApplication())
    }

}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return  androidApplication.getSharedPreferences("default",  android.content.Context.MODE_PRIVATE)
}