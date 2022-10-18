package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.model.MintingProperties
import com.kycdao.android.sdk.model.MintingTransactionResult

interface WalletSession {

    val id: String
    /**
     * Returns a list of all available wallets
     */
    fun getAvailableWallets() : List<String>?
    /**
     * Returns the chain id in CAIP-2 form
     */
    fun getChainId() : String
    /**
     * Signs the message with the given wallets personal token
     *
     * @param walletAddress The address to sign with
     * @param message The message to sign
     *
     * @return The created signed message
     */
    suspend fun personalSign(walletAddress: String, message: String) : String

    /**
     * Initiates a minting transaction using the given wallet
     *
     * @param walletAddress The address from which we want to mint
     * @param mintingProperties The properties necessary for minting (gasPrice, gasAmount, contractAddress, contractABI)
     *
     * @return Result of the minting containing the transaction hash
     */
    suspend fun sendMintingTransaction(walletAddress: String, mintingProperties: MintingProperties) : MintingTransactionResult
}