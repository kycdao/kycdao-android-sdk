//[app](../../../index.md)/[com.kycdao.android.sdk.wallet](../index.md)/[WalletConnectSession](index.md)

# WalletConnectSession

[androidJvm]\
class [WalletConnectSession](index.md)(val wcSession: [Session](../../com.kycdao.android.sdk.walletconnect/-session/index.md), val wcConfig: [Session.Config](../../com.kycdao.android.sdk.walletconnect/-session/-config/index.md)) : [WalletSession](../-wallet-session/index.md)

## Constructors

| | |
|---|---|
| [WalletConnectSession](-wallet-connect-session.md) | [androidJvm]<br>fun [WalletConnectSession](-wallet-connect-session.md)(wcSession: [Session](../../com.kycdao.android.sdk.walletconnect/-session/index.md), wcConfig: [Session.Config](../../com.kycdao.android.sdk.walletconnect/-session/-config/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [addListenerOnEstablished](add-listener-on-established.md) | [androidJvm]<br>fun [addListenerOnEstablished](add-listener-on-established.md)(approvedCallback: FunctionWithSession) |
| [getAvailableWallets](get-available-wallets.md) | [androidJvm]<br>fun [getAvailableWallets](get-available-wallets.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [getChainId](get-chain-id.md) | [androidJvm]<br>open override fun [getChainId](get-chain-id.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns the chain id in CAIP-2 form |
| [hasValidToken](has-valid-token.md) | [androidJvm]<br>open suspend override fun [hasValidToken](has-valid-token.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), verificationConfig: [SmartContractConfig](../../com.kycdao.android.sdk.model/-smart-contract-config/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [personalSign](personal-sign.md) | [androidJvm]<br>open suspend override fun [personalSign](personal-sign.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Signs the message with the given wallets personal token |
| [sendMintingTransaction](send-minting-transaction.md) | [androidJvm]<br>open suspend override fun [sendMintingTransaction](send-minting-transaction.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), mintingProperties: [MintingProperties](../../com.kycdao.android.sdk.model.functions.mint/-minting-properties/index.md)): [MintingTransactionResult](../../com.kycdao.android.sdk.model.functions.mint/-minting-transaction-result/index.md)<br>Initiates a minting transaction using the given wallet |

## Properties

| Name | Summary |
|---|---|
| [accounts](accounts.md) | [androidJvm]<br>val [accounts](accounts.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [icons](icons.md) | [androidJvm]<br>val [icons](icons.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [id](id.md) | [androidJvm]<br>open override val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [url](url.md) | [androidJvm]<br>val [url](url.md): [WalletConnectURL](../../com.kycdao.android.sdk.model/-wallet-connect-u-r-l/index.md) |
| [wcConfig](wc-config.md) | [androidJvm]<br>val [wcConfig](wc-config.md): [Session.Config](../../com.kycdao.android.sdk.walletconnect/-session/-config/index.md) |
| [wcSession](wc-session.md) | [androidJvm]<br>val [wcSession](wc-session.md): [Session](../../com.kycdao.android.sdk.walletconnect/-session/index.md) |
