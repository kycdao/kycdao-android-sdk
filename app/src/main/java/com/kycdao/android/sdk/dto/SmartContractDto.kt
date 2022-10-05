package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.SmartContractConfig

data class SmartContractConfigDto(
    val address: String,
    val payment_discount_percent: Int
)

fun SmartContractConfigDto.toModel() : SmartContractConfig{
    return SmartContractConfig(
        address = this.address,
        paymentDiscountPercent = this.payment_discount_percent
    )
}