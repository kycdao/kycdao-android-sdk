package com.kycdao.android.sdk.util

import com.kycdao.android.sdk.exceptions.toException
import com.kycdao.android.sdk.model.functions.ABIFunction
import kotlinx.coroutines.future.await
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import timber.log.Timber
import java.math.BigInteger



suspend fun <T > Web3j.callABIFunction(abiFunction: ABIFunction<T>) : T{
	val transaction = Transaction.createFunctionCallTransaction(
		/* from = */ abiFunction.walletAddress,
		/* nonce = */ null,
		/* gasPrice = */ null,
		/* gasLimit = */ null,
		/* to = */ abiFunction.contractAddress,
		/* data = */ FunctionEncoder.encode(abiFunction.function)
	)
	val ethCallResponse =
		ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().await()
	if (ethCallResponse.error != null) {
		Timber.d(abiFunction.function.name)
		throw ethCallResponse.error.toException()
	}
	val result = FunctionReturnDecoder.decode(
		ethCallResponse.value,
		abiFunction.function.outputParameters
	)
	return result[0].value as T
}