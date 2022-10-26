package com.kycdao.android.sdk.model.functions.token_validation

import org.web3j.abi.TypeReference

data class HasValidTokenFunction(val address: String) : org.web3j.abi.datatypes.Function(
	"hasValidToken",
	listOf(org.web3j.abi.datatypes.Address(address)),
	listOf(TypeReference.create(org.web3j.abi.datatypes.Bool::class.java))
)