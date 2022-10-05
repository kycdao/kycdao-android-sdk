package com.kycdao.android.sdk.network

//import hu.bitraptors.core.api.response.NetworkResponseAdapterFactory
import com.kycdao.android.sdk.network.api.APIService
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    single<CookieJar> {
        SessionCookieJar(get())
    }

    single {
        val loggingInterceptor: HttpLoggingInterceptor = get()

        OkHttpClient.Builder()
            .cookieJar(get())
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single{
        Retrofit.Builder()
            .client(get()).apply {
                addConverterFactory(get<MoshiConverterFactory>())
//                addCallAdapterFactory(NetworkResponseAdapterFactory())
            }
//            .baseUrl(baseUrl)
            .baseUrl("https://staging.kycdao.xyz/api/frontend/")
            .build()
    }

    single {
        val retrofit = get<Retrofit>()
        retrofit.create(APIService::class.java)
    }

    single<NetworkDatasource> {
        NetworkDatasourceImpl(api = get())
    }

}