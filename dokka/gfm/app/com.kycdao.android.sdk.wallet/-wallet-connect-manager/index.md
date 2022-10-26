//[app](../../../index.md)/[com.kycdao.android.sdk.wallet](../index.md)/[WalletConnectManager](index.md)

# WalletConnectManager

[androidJvm]\
object [WalletConnectManager](index.md) : [CustomKoinComponent](../../com.kycdao.android.sdk/-custom-koin-component/index.md)

A helper object responsible for establishing the connection to a wallet via WalletConnect

## Functions

| Name | Summary |
|---|---|
| [connectWallet](connect-wallet.md) | [androidJvm]<br>fun [connectWallet](connect-wallet.md)()<br>Starts connection process to a wallet via WalletConnect |
| [getKoin](../../com.kycdao.android.sdk/-custom-koin-component/get-koin.md) | [androidJvm]<br>open override fun [getKoin](../../com.kycdao.android.sdk/-custom-koin-component/get-koin.md)(): Koin |
| [getUri](get-uri.md) | [androidJvm]<br>fun [getUri](get-uri.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Gets the uri needed to show Qr code |
| [subscribeOnConnectionEstablished](subscribe-on-connection-established.md) | [androidJvm]<br>fun [subscribeOnConnectionEstablished](subscribe-on-connection-established.md)(onConnectionEstablished: ([WalletConnectSession](../-wallet-connect-session/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Sets a callback to run when a connection with a wallet is successfully established |
