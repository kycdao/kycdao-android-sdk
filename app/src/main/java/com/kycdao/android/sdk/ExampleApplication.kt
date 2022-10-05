package com.kycdao.android.sdk

import androidx.multidex.MultiDexApplication
import com.kycdao.android.sdk.db.dbModule
import com.kycdao.android.sdk.network.moshiModule
import com.kycdao.android.sdk.network.networkModule
import com.squareup.moshi.Moshi
import com.kycdao.android.sdk.server.BridgeServer
import com.kycdao.android.sdk.ui.uiModule
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kycdao.android.sdk.BuildConfig
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.walletconnect.impls.*
import timber.log.Timber
import java.io.File

class ExampleApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initMoshi()
        initClient()
        initBridge()
        initSessionStorage()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ExampleApplication)
            modules(
                appModule,
                moshiModule,
                networkModule,
                domainModule,
                dbModule,
                uiModule,
                web3Module,
            )
        }
    }

    private fun initClient() {
        client = OkHttpClient.Builder().build()
    }

    private fun initMoshi() {
        moshi = Moshi.Builder().apply {
            add(KotlinJsonAdapterFactory())
        }.build()
    }


    private fun initBridge() {
        bridge = BridgeServer(moshi)
        bridge.start()
    }

    private fun initSessionStorage() {
        storage = FileWCSessionStore(File(cacheDir, "session_store.json").apply { createNewFile() }, moshi)
    }

    companion object {
        lateinit var client: OkHttpClient
        lateinit var moshi: Moshi
        lateinit var bridge: BridgeServer
        lateinit var storage: WCSessionStore
    }
}
