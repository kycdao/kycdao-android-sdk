package com.kycdao.android.sdk.usecase

import java.math.BigInteger

interface EthGasPriceUseCase {
    suspend operator fun invoke() : BigInteger
}