//[app](../../../index.md)/[com.kycdao.android.sdk.wallet](../index.md)/[WalletSession](index.md)

# WalletSession

[androidJvm]\
interface [WalletSession](index.md)

## Functions

| Name | Summary |
|---|---|
| [getChainId](get-chain-id.md) | [androidJvm]<br>abstract fun [getChainId](get-chain-id.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns the chain id in CAIP-2 form |
| [hasValidToken](has-valid-token.md) | [androidJvm]<br>abstract suspend fun [hasValidToken](has-valid-token.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), verificationConfig: [SmartContractConfig](../../com.kycdao.android.sdk.model/-smart-contract-config/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [personalSign](personal-sign.md) | [androidJvm]<br>abstract suspend fun [personalSign](personal-sign.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Signs the message with the given wallets personal token |
| [sendMintingTransaction](send-minting-transaction.md) | [androidJvm]<br>abstract suspend fun [sendMintingTransaction](send-minting-transaction.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), mintingProperties: [MintingProperties](../../com.kycdao.android.sdk.model.functions.mint/-minting-properties/index.md)): [MintingTransactionResult](../../com.kycdao.android.sdk.model.functions.mint/-minting-transaction-result/index.md)<br>Initiates a minting transaction using the given wallet |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [androidJvm]<br>abstract val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WalletConnectSession](../-wallet-connect-session/index.md) |
