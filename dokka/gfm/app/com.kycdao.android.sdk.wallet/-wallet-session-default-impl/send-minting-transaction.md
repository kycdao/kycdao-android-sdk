//[app](../../../index.md)/[com.kycdao.android.sdk.wallet](../index.md)/[WalletSessionDefaultImpl](index.md)/[sendMintingTransaction](send-minting-transaction.md)

# sendMintingTransaction

[androidJvm]\
open suspend override fun [sendMintingTransaction](send-minting-transaction.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), mintingProperties: [MintingProperties](../../com.kycdao.android.sdk.model/-minting-properties/index.md)): [MintingTransactionResult](../../com.kycdao.android.sdk.model/-minting-transaction-result/index.md)

Initiates a minting transaction using the given wallet

#### Return

Result of the minting containing the transaction hash

## Parameters

androidJvm

| | |
|---|---|
| walletAddress | The address from which we want to mint |
| mintingProperties | The properties necessary for minting (gasPrice, gasAmount, contractAddress, contractABI) |
