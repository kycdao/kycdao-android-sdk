package com.kycdao.android.sdk.model.functions

import com.kycdao.android.sdk.util.web3jEncodedUint32
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Function

data class KYCGetRequiredMintCostForCodeFunction(val authCode: String, val address: String) : Function(
	"getRequiredMintCostForCode",
	listOf(authCode.web3jEncodedUint32(),Address(address)),
	listOf(TypeReference.create(org.web3j.abi.datatypes.Uint::class.java))
)