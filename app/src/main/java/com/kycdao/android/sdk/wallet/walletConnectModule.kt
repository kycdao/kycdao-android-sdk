package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.server.BridgeServer
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.kycdao.android.sdk.walletconnect.impls.FileWCSessionStore
import com.kycdao.android.sdk.walletconnect.impls.WCSessionStore
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

/***
 * @suppress
 */
val walletConnectModule = module{
    single<Moshi>() {
        Moshi.Builder().apply {
            add(KotlinJsonAdapterFactory())
        }
            .build()
    }

    single<WCSessionStore>{ FileWCSessionStore(File(androidApplication().cacheDir, "session_store.json").apply { createNewFile() }, get()) }
    single<BridgeServer>{ BridgeServer(get()) }
    single<OkHttpClient>(named("WalletConnectClient")){ OkHttpClient.Builder().build()}
}