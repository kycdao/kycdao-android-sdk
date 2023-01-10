package com.kycdao.android.sdk.model.functions
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function

class KYCGetSubscriptionCostDecimalFunction : Function(
	"SUBSCRIPTION_COST_DECIMALS",
	emptyList(),
	listOf(TypeReference.create(org.web3j.abi.datatypes.Uint::class.java))
)