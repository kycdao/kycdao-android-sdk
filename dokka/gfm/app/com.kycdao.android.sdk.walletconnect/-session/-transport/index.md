//[app](../../../../index.md)/[com.kycdao.android.sdk.walletconnect](../../index.md)/[Session](../index.md)/[Transport](index.md)

# Transport

[androidJvm]\
interface [Transport](index.md)

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [androidJvm]<br>interface [Builder](-builder/index.md) |
| [Message](-message/index.md) | [androidJvm]<br>data class [Message](-message/index.md)(val topic: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val type: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val payload: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [Status](-status/index.md) | [androidJvm]<br>sealed class [Status](-status/index.md) |

## Functions

| Name | Summary |
|---|---|
| [close](close.md) | [androidJvm]<br>abstract fun [close](close.md)() |
| [connect](connect.md) | [androidJvm]<br>abstract fun [connect](connect.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isConnected](is-connected.md) | [androidJvm]<br>abstract fun [isConnected](is-connected.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [send](send.md) | [androidJvm]<br>abstract fun [send](send.md)(message: [Session.Transport.Message](-message/index.md)) |

## Inheritors

| Name |
|---|
| [OkHttpTransport](../../../com.kycdao.android.sdk.walletconnect.impls/-ok-http-transport/index.md) |
