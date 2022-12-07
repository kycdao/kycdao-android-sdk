package spoti.hu.kyctestapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import spoti.hu.kyctestapp.manager.SDKManager
import spoti.hu.kyctestapp.manager.SDKManagerImpl
import timber.log.Timber


class KycApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
        Timber.plant(Timber.DebugTree())
    }

    private fun initDI() {
        startKoin {
            androidContext(this@KycApplication)
            modules(
                listOf(
                    modules
                )
            )
        }
    }
}

val modules = module {
    single<SDKManager> {
        SDKManagerImpl()
    }
}
