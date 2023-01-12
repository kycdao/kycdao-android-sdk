package spoti.hu.kyctestapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.bitraptors.networking.api.httpClient
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.dsl.module
import spoti.hu.kyctestapp.manager.SDKManager
import spoti.hu.kyctestapp.manager.SDKManagerImpl
import spoti.hu.kyctestapp.navigation.NavigationManager
import spoti.hu.kyctestapp.navigation.NavigationManagerImpl
import spoti.hu.kyctestapp.viewmodel.InformationRequestViewModel
import spoti.hu.kyctestapp.viewmodel.SelectMembershipViewModel
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
                    viewModelsModule
                )
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(SvgDecoder.Factory())
            }
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
            .crossfade(true)
            .respectCacheHeaders(false)
            .build()
    }
}

val modules = module {
    single<SDKManager> {
        SDKManagerImpl()
    }
    single<NavigationManager>{
        NavigationManagerImpl(get())
    }
}
val viewModelsModule = module {
    viewModel { InformationRequestViewModel(get()) }
    viewModel { SelectMembershipViewModel(get()) }

}