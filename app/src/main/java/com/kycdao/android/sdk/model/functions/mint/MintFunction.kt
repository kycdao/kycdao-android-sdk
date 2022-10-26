package com.kycdao.android.sdk.model.functions.mint

import com.kycdao.android.sdk.util.web3jEncodedUint32
import org.web3j.abi.datatypes.Function


data class MintFunction(val authCode: String) : Function(
	"mint",
	listOf(authCode.web3jEncodedUint32()),
	listOf()
)