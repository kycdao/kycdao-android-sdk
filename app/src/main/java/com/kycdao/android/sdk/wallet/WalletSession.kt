package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.model.MintingProperties
import com.kycdao.android.sdk.model.MintingTransactionResult

interface WalletSession {
    fun getAvailableWallets() : List<String>?
    fun getChainId() : String
    suspend fun personalSign(walletAddress: String, message: String) : String
    suspend fun sendMintingTransaction(walletAddress: String, mintingProperties: MintingProperties) : MintingTransactionResult
}