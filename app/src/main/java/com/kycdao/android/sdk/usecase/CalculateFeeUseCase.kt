package com.kycdao.android.sdk.usecase

import java.math.BigInteger

interface CalculateFeeUseCase {
    suspend operator fun invoke() : BigInteger
}