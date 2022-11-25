package com.bitraptors.networking.api.models

import java.io.IOException

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ApiResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val exception: Throwable?) : ApiResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

@Throws(ClassCastException::class)
suspend fun <T : Any, R : Any> ApiResult<T>.safeMapResultTo(
    onError: (Throwable?) -> ApiResult.Error = {
        ApiResult.Error(IOException("Mapping result failed", it))
    },
    map: suspend (T) -> R
): ApiResult<R> = when (this) {
    is ApiResult.Success -> try {
        ApiResult.Success<R>(map.invoke(data))
    } catch (e: Exception) {
        onError(e)
    }
    is ApiResult.Error -> onError(exception)
}

suspend fun <T : Any, R : Any> ApiResult<T>.mapSuccess(
    onError: (Throwable?) -> ApiResult.Error = {
        ApiResult.Error(IOException("Mapping result failed", it))
    },
    map: suspend (T) -> ApiResult<R>
): ApiResult<R> = when (this) {
    is ApiResult.Success -> map.invoke(data)
    is ApiResult.Error -> onError(exception)
}

suspend fun <T : Any> ApiResult<T>.mapFailure(
    map: suspend (ApiResult.Error) -> ApiResult<T>
): ApiResult<T> = when (this) {
    is ApiResult.Success -> this
    is ApiResult.Error -> map.invoke(this)
}

