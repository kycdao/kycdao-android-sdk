package com.kycdao.android.sdk.network

//import com.bitraptors.networking.api.response.NetworkResponseAdapterFactory
import com.bitraptors.networking.api.retrofit
import com.kycdao.android.sdk.network.api.APIService
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://staging.kycdao.xyz/api/frontend/"

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
        retrofit(
            get<MoshiConverterFactory>(),
            httpClient = get(),
            baseUrl = BASE_URL
        )
    }

    single {
        val retrofit = get<Retrofit>()
        retrofit.create(APIService::class.java)
    }

    single<NetworkDatasource> {
        NetworkDatasourceImpl(api = get())
    }

}