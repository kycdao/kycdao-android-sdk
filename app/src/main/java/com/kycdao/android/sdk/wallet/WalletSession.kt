package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.model.SmartContractConfig
import com.kycdao.android.sdk.model.functions.mint.MintingProperties
import com.kycdao.android.sdk.model.functions.mint.MintingTransactionResult

interface WalletSession {

	val id: String

	/**
	 * Returns the chain id in CAIP-2 form
	 */
	fun getChainId(): String

	/**
	 * Signs the message with the given wallets personal token
	 *
	 * @param walletAddress The address to sign with
	 * @param message The message to sign
	 *
	 * @return The created signed message
	 */
	suspend fun personalSign(walletAddress: String, message: String): String

	/**
	 * Initiates a minting transaction using the given wallet
	 *
	 * @param walletAddress The address from which we want to mint
	 * @param mintingProperties The properties necessary for minting (gasPrice, gasAmount, contractAddress, contractABI)
	 *
	 * @return Result of the minting containing the transaction hash
	 */
	suspend fun sendMintingTransaction(
		walletAddress: String,
		mintingProperties: MintingProperties
	): MintingTransactionResult

	suspend fun hasValidToken(
		walletAddress: String,
		verificationConfig: SmartContractConfig,
	): Boolean

}