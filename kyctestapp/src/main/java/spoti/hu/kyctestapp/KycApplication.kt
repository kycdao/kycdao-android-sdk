package spoti.hu.kyctestapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.bitraptors.networking.api.httpClient
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.dsl.module
import spoti.hu.kyctestapp.manager.SDKManager
import spoti.hu.kyctestapp.manager.SDKManagerImpl
import timber.log.Timber


class KycApplication : Application(), ImageLoaderFactory {

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
                    modules,
                    networkModule
                )
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    // Set the max size to 25% of the app's available memory.
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(filesDir.resolve("image_cache"))
                    .maxSizeBytes(512L * 1024 * 1024) // 512MB
                    .build()
            }
            .okHttpClient {
                val okHttpClient : OkHttpClient = get()
                okHttpClient
            }
            .crossfade(true)
            .respectCacheHeaders(false)
            .build()
    }
}

val modules = module {
    single<SDKManager> {
        SDKManagerImpl()
    }
}

val networkModule = module {
    factory {
        httpClient(
            interceptors = arrayOf(CallInterceptorFactory(get()).createHeaderChangingInterceptor()),
            useLogging = BuildConfig.DEBUG,
            timeOutInSec = 20
        )
    }
}