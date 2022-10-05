package com.kycdao.android.sdk.network.api

data class AuthorizeMintingRequestBody(
    val blockchain_account_id: Long,
    val network: String = "PolygonMumbai",
    val verification_type: String = "KYC",
    val selected_image_id: String
)
