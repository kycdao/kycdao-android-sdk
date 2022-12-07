package com.kycdao.android.sdk.model.functions
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.generated.Uint32

data class KYCGetRequiredMintCostForSecondsFunction(val timeInSeconds: UInt) : Function(
	"getRequiredMintCostForSeconds",
	listOf(Uint32(timeInSeconds.toLong())),
	listOf(TypeReference.create(org.web3j.abi.datatypes.Uint::class.java))
)