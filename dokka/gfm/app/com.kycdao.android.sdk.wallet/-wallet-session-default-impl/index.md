//[app](../../../index.md)/[com.kycdao.android.sdk.wallet](../index.md)/[WalletSessionDefaultImpl](index.md)

# WalletSessionDefaultImpl

[androidJvm]\
class [WalletSessionDefaultImpl](index.md)(val wcSession: Session, val wcConfig: Session.Config) : [WalletSession](../-wallet-session/index.md)

## Constructors

| | |
|---|---|
| [WalletSessionDefaultImpl](-wallet-session-default-impl.md) | [androidJvm]<br>fun [WalletSessionDefaultImpl](-wallet-session-default-impl.md)(wcSession: Session, wcConfig: Session.Config) |

## Functions

| Name | Summary |
|---|---|
| [addListenerOnEstablished](add-listener-on-established.md) | [androidJvm]<br>fun [addListenerOnEstablished](add-listener-on-established.md)(approvedCallback: FunctionWithSession) |
| [getAvailableWallets](get-available-wallets.md) | [androidJvm]<br>open override fun [getAvailableWallets](get-available-wallets.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?<br>Returns a list of all available wallets |
| [getChainId](get-chain-id.md) | [androidJvm]<br>open override fun [getChainId](get-chain-id.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns the chain id in CAIP-2 form |
| [personalSign](personal-sign.md) | [androidJvm]<br>open suspend override fun [personalSign](personal-sign.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Valami komment |
| [sendMintingTransaction](send-minting-transaction.md) | [androidJvm]<br>open suspend override fun [sendMintingTransaction](send-minting-transaction.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), mintingProperties: [MintingProperties](../../com.kycdao.android.sdk.model/-minting-properties/index.md)): [MintingTransactionResult](../../com.kycdao.android.sdk.model/-minting-transaction-result/index.md)<br>Initiates a minting transaction using the given wallet |

## Properties

| Name | Summary |
|---|---|
| [wcConfig](wc-config.md) | [androidJvm]<br>val [wcConfig](wc-config.md): Session.Config |
| [wcSession](wc-session.md) | [androidJvm]<br>val [wcSession](wc-session.md): Session |
