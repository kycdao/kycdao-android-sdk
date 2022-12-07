package com.kycdao.android.sdk.model.functions

import org.web3j.abi.FunctionEncoder
import org.web3j.protocol.core.methods.request.Transaction
import java.math.BigInteger

data class ABIFunction<T>(
	val function: org.web3j.abi.datatypes.Function,
	val contractAddress: String,
	val walletAddress: String
){
	fun toTransaction(value: BigInteger? = null) : Transaction {
		return Transaction.createFunctionCallTransaction(
			walletAddress,
			null,
			null,
			null,
			contractAddress,
			value,
			FunctionEncoder.encode(function)
		)
	}
}