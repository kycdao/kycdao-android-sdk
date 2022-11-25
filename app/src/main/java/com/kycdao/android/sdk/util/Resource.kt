package com.kycdao.android.sdk.util

sealed class Resource<out T: Any> {
	data class Success<T: Any>(val data: T) : Resource<T>()
	data class Failure(val message: String,val throwable: Throwable? = null) : Resource<Nothing>()
}