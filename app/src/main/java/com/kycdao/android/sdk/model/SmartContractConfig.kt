package com.kycdao.android.sdk.model

import com.kycdao.android.sdk.dto.SmartContractConfigDto

data class SmartContractConfig(
    val address: String,
    val paymentDiscountPercent: Int,
)

