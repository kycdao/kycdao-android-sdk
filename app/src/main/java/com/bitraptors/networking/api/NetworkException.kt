package com.bitraptors.networking.api

import java.io.IOException

open class NetworkException(override val cause: Throwable?) : IOException(cause)
class NoInternetException : NetworkException(null)
class UnknownException(override val cause: Throwable?) : NetworkException(cause)

class ApiCommunicationFailure(val response: Any) : Exception()