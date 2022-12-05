package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.model.SmartContractConfig
import com.kycdao.android.sdk.model.functions.mint.MintingProperties
import com.kycdao.android.sdk.model.functions.mint.MintingTransactionResult

/**
 * The interface describes a communication session with a wallet that can be used during the verification process.
 *
 * ### Wallets
 * Use this interface, when you want to integrate the kycDAO SDK to you wallet. Provide a concrete implementation of the protocol in a class. Learn more at Wallet Integration about integrating the SDK to a wallet.
 *
 * ### DApps
 * For DApps integrating the kycDAO SDK, you will likely won’t have to use this interface directly. WalletConnect should be used to connect your DApp to a supported Wallet.
 *
 */
interface WalletSession {

	val id: String

	/**
	 * Returns the chain id in <a href="https://github.com/ChainAgnostic/CAIPs/blob/master/CAIPs/caip-2.md">CAIP-2 format</a>
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

}