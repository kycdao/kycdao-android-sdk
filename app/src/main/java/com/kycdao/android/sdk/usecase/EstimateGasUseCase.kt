package com.kycdao.android.sdk.usecase

import java.math.BigInteger

interface EstimateGasUseCase {
    suspend operator fun invoke() : BigInteger
}