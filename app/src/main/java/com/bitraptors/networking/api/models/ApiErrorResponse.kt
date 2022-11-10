package com.bitraptors.networking.api.models

data class ApiErrorResponse(
    val error: String,
    val statusCode: Int,
    val localizedErrorMessage: String?
)