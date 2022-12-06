package com.kycdao.android.sdk.network.request_models

import com.kycdao.android.sdk.model.VerificationType

data class AuthorizeMintingRequestBody private constructor(
    val blockchain_account_id: Long,
    val network: String = "PolygonMumbai",
    val verification_type: String = "KYC",
    val selected_image_id: String,
    val subscription_duration: String
){
    constructor(accountId : Long,
                network: String,
                verificationType: VerificationType,
                selected_image_id: String,
                subscriptionDuration : UInt) : this(
        accountId, network, verificationType.name, selected_image_id, "P${subscriptionDuration}Y")



}
