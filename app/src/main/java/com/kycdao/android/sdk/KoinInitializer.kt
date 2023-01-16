package com.kycdao.android.sdk

import android.content.Context
import androidx.startup.Initializer
import com.kycdao.android.sdk.network.moshiModule
import com.kycdao.android.sdk.network.networkModule
import com.kycdao.android.sdk.wallet.walletConnectModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import timber.log.Timber

object MyKoinContext{
    var koinApp: KoinApplication?= null
}
open class CustomKoinComponent : KoinComponent {
    // Override default Koin instance, initially target on GlobalContext to yours
    override fun getKoin(): Koin = MyKoinContext.koinApp?.koin!!
}

internal class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        println("initialized koin")
        MyKoinContext.koinApp = koinApplication {
            androidLogger(Level.ERROR)
            androidContext(context)
            modules(
                appModule,
                walletConnectModule,
                networkModule,
                domainModule,
                web3Module,
                moshiModule
            )
        }
        return MyKoinContext.koinApp!!
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        emptyList()
}