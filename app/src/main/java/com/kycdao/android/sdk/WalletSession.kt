package com.kycdao.android.sdk

import com.kycdao.android.sdk.model.MintingProperties

interface WalletSession {
    fun getChainId() : String // CAIP-2: “eip155:” + chainId
    fun personalSign(walletAddress: String, message: String) : String
    fun sendMintingTransaction(walletAddress: String, mintingProperties: MintingProperties) : String
}