package com.kycdao.android.sdk.model

import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.generated.Uint32
import java.math.BigInteger


data class MintFunction(val authCode : String) : Function(
    "mint",
    listOf(Uint32(BigInteger.valueOf(authCode.toLong()))),
    listOf()
)