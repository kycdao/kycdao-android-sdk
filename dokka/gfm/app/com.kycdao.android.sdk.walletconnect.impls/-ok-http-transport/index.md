//[app](../../../index.md)/[com.kycdao.android.sdk.walletconnect.impls](../index.md)/[OkHttpTransport](index.md)

# OkHttpTransport

[androidJvm]\
class [OkHttpTransport](index.md)(client: OkHttpClient, serverUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), statusHandler: ([Session.Transport.Status](../../com.kycdao.android.sdk.walletconnect/-session/-transport/-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), messageHandler: ([Session.Transport.Message](../../com.kycdao.android.sdk.walletconnect/-session/-transport/-message/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), moshi: Moshi) : WebSocketListener, [Session.Transport](../../com.kycdao.android.sdk.walletconnect/-session/-transport/index.md)

## Constructors

| | |
|---|---|
| [OkHttpTransport](-ok-http-transport.md) | [androidJvm]<br>fun [OkHttpTransport](-ok-http-transport.md)(client: OkHttpClient, serverUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), statusHandler: ([Session.Transport.Status](../../com.kycdao.android.sdk.walletconnect/-session/-transport/-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), messageHandler: ([Session.Transport.Message](../../com.kycdao.android.sdk.walletconnect/-session/-transport/-message/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), moshi: Moshi) |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [androidJvm]<br>class [Builder](-builder/index.md)(val client: OkHttpClient, val moshi: Moshi) : [Session.Transport.Builder](../../com.kycdao.android.sdk.walletconnect/-session/-transport/-builder/index.md) |

## Functions

| Name | Summary |
|---|---|
| [close](close.md) | [androidJvm]<br>open override fun [close](close.md)() |
| [connect](connect.md) | [androidJvm]<br>open override fun [connect](connect.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isConnected](is-connected.md) | [androidJvm]<br>open override fun [isConnected](is-connected.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onClosed](on-closed.md) | [androidJvm]<br>open override fun [onClosed](on-closed.md)(webSocket: WebSocket, code: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), reason: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [onClosing](index.md#-965341629%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onClosing](index.md#-965341629%2FFunctions%2F-912451524)(webSocket: WebSocket, code: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), reason: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [onFailure](on-failure.md) | [androidJvm]<br>open override fun [onFailure](on-failure.md)(webSocket: WebSocket, t: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html), response: Response?) |
| [onMessage](on-message.md) | [androidJvm]<br>open override fun [onMessage](on-message.md)(webSocket: WebSocket, text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>open fun [onMessage](index.md#-413335953%2FFunctions%2F-912451524)(webSocket: WebSocket, bytes: ByteString) |
| [onOpen](on-open.md) | [androidJvm]<br>open override fun [onOpen](on-open.md)(webSocket: WebSocket, response: Response) |
| [send](send.md) | [androidJvm]<br>open override fun [send](send.md)(message: [Session.Transport.Message](../../com.kycdao.android.sdk.walletconnect/-session/-transport/-message/index.md)) |
