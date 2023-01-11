package com.kycdao.android.sdk.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

val moshiModule = module {

    single<Moshi>(named("network")) {
        Moshi.Builder().apply {
            add(KotlinJsonAdapterFactory())
            add(Date::class.java, Rfc3339DateJsonAdapter())
        }.build()
    }
    single { MoshiConverterFactory.create(get(named("network"))) }

}