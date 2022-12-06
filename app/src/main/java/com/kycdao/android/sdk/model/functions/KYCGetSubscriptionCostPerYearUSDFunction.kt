package com.kycdao.android.sdk.model.functions

import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function


class KYCGetSubscriptionCostPerYearUSDFunction : Function(
	"getSubscriptionCostPerYearUSD",
	emptyList(),
	listOf(TypeReference.create(org.web3j.abi.datatypes.Uint::class.java))
)