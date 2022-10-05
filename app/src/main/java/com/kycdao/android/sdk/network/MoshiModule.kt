package com.kycdao.android.sdk.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory

val moshiModule = module {

    single<Moshi> {
        Moshi.Builder().apply {
            add(KotlinJsonAdapterFactory())
        }
            .build()
    }
    single { MoshiConverterFactory.create(get()) }

}