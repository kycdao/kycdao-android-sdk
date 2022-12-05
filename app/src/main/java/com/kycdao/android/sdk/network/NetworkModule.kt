package com.kycdao.android.sdk.network

//import com.bitraptors.networking.api.response.NetworkResponseAdapterFactory
import com.bitraptors.networking.api.retrofit
import com.kycdao.android.sdk.network.api.APIService
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.parametersOf
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

    single{ (url : String) ->
        retrofit(
            get<MoshiConverterFactory>(),
            httpClient = get(),
            baseUrl = "$url/api/frontend/"
        )
    }

    single { (url : String) ->
        val retrofit = get<Retrofit>(){ parametersOf(url)}
        retrofit.create(APIService::class.java)
    }

    single<NetworkDatasource> { (url : String) ->
        NetworkDatasourceImpl(api = get() { parametersOf(url)})
    }

}