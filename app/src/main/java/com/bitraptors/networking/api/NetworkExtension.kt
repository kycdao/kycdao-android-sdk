package com.bitraptors.networking.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.bitraptors.networking.api.response.NetworkResponseAdapterFactory
import com.bitraptors.networking.api.models.ApiResult
import com.bitraptors.networking.api.models.ApiErrorResponse
import com.bitraptors.networking.api.models.NetworkErrorResponse
import com.bitraptors.networking.api.models.NetworkResponse
import com.bitraptors.networking.api.models.NetworkType
import kotlinx.coroutines.CompletableDeferred
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import java.io.IOException
import java.util.concurrent.TimeUnit

fun hasInternetConnection(context: Context?): Boolean {
    return context?.let { networkType(it) != NetworkType.Offline } ?: false
}

fun networkType(context: Context): NetworkType {
    val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as? ConnectivityManager
    val nw = connectivityManager?.activeNetwork ?: return NetworkType.Offline
    val actNw = connectivityManager.getNetworkCapabilities(nw)
        ?: return NetworkType.Offline
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkType.Wifi
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkType.Mobile
        else -> NetworkType.Offline
    }
}

fun httpClient(
    vararg interceptors: Interceptor,
    useLogging: Boolean,
    timeOutInSec: Long
): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder().apply {
        dispatcher(Dispatcher().apply { maxRequestsPerHost = maxRequests })
        if (useLogging) addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        interceptors.forEach { addInterceptor(it) }

        connectTimeout(timeOutInSec, TimeUnit.SECONDS)
    }
    return clientBuilder.build()
}

fun retrofit(
    vararg factories: Converter.Factory,
    baseUrl: String,
    httpClient: OkHttpClient
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addCallAdapterFactory(NetworkResponseAdapterFactory())
    .client(httpClient).apply {
        factories.forEach { addConverterFactory(it) }
    }.build()


suspend fun <T : Any, R : Any> ApiResult<T>.safeMapResultTo(
    onError: (Throwable?) -> ApiResult.Error = {
        ApiResult.Error(it)
    },
    map: suspend (T) -> R
): ApiResult<R> = when (this) {
    is ApiResult.Success -> try {
        ApiResult.Success<R>(map.invoke(data))
    } catch (e: Exception) {
        ApiResult.Error(IOException("Network result mapping failed", e))
    }
    is ApiResult.Error -> ApiResult.Error(exception)
}

fun <T : Any, E : Any> NetworkResponse<T, ApiErrorResponse>.mapBody(map: (T) -> E): NetworkResponse<E, ApiErrorResponse> {
    return when (this) {
        is NetworkResponse.Success -> {
            try {
                NetworkResponse.Success(map(data))
            } catch (e: Exception) {
                NetworkResponse.Failure(NetworkErrorResponse.UnknownError(e))
            }
        }
        is NetworkResponse.Failure<*> -> this
    }
}

suspend fun <T : Any, E : Any> Call<NetworkResponse<T, ApiErrorResponse>>.toResponse(
    map: (T) -> E
): NetworkResponse<E, ApiErrorResponse> {
    val result = CompletableDeferred<NetworkResponse<E, ApiErrorResponse>>()
    enqueue(object : Callback<NetworkResponse<T, ApiErrorResponse>> {
        override fun onResponse(
            call: Call<NetworkResponse<T, ApiErrorResponse>>,
            response: Response<NetworkResponse<T, ApiErrorResponse>>
        ) {
            val content = response.body()
            result.complete(
                content?.mapBody(map)
                    ?: NetworkResponse.Failure(NetworkErrorResponse.UnknownError())
            )
        }

        override fun onFailure(call: Call<NetworkResponse<T, ApiErrorResponse>>, t: Throwable) {
            result.complete(NetworkResponse.Failure(NetworkErrorResponse.UnknownError(t)))
        }
    })
    return result.await()
}
