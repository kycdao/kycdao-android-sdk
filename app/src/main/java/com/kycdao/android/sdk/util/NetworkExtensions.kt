package com.kycdao.android.sdk.util

import com.bitraptors.networking.api.models.NetworkErrorResponse
import com.bitraptors.networking.api.models.NetworkResponse
import com.kycdao.android.sdk.exceptions.KycNetworkCallException
import com.kycdao.android.sdk.model.KYCErrorResponse


fun <T: Any> NetworkResponse<T,KYCErrorResponse>.handleResponse() : T{
	when(this){
		is NetworkResponse.Failure<*> -> {
			throw KycNetworkCallException(this.error as NetworkErrorResponse<KYCErrorResponse>)
		}
		is NetworkResponse.Success -> {
			return data
		}
	}
}