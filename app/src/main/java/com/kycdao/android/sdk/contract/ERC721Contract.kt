package com.kycdao.android.sdk.contract

import com.kycdao.android.sdk.walletconnect.nullOnThrow
import org.web3j.abi.EventValues
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Event
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.ens.contracts.generated.ENS.TransferEventResponse
import org.web3j.protocol.core.methods.response.Log
import org.web3j.tx.Contract
import java.math.BigInteger


internal object ERC721Contract {

	val TRANSFER_EVENT = Event(
		"Transfer",
		listOf<TypeReference<*>>(
			TypeReference.create(Address::class.java,true),
			TypeReference.create(Address::class.java,true),
			TypeReference.create(Uint256::class.java,true),
		)
	)
	fun getTopicID(log: Log) : String?{
		val valueList : EventValues =nullOnThrow {  Contract.staticExtractEventParameters(TRANSFER_EVENT,log)} ?: return null
		return (valueList.indexedValues[2].value as BigInteger).toString(10)
	}
}