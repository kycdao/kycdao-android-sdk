//[app](../../../../index.md)/[com.kycdao.android.sdk.walletconnect](../../index.md)/[Session](../index.md)/[MethodCall](index.md)

# MethodCall

[androidJvm]\
sealed class [MethodCall](index.md)

## Types

| Name | Summary |
|---|---|
| [Custom](-custom/index.md) | [androidJvm]<br>data class [Custom](-custom/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val method: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;*&gt;?) : [Session.MethodCall](index.md) |
| [PersonalSignMessage](-personal-sign-message/index.md) | [androidJvm]<br>data class [PersonalSignMessage](-personal-sign-message/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val address: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Session.MethodCall](index.md) |
| [Response](-response/index.md) | [androidJvm]<br>data class [Response](-response/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val result: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, val error: [Session.Error](../-error/index.md)? = null) : [Session.MethodCall](index.md) |
| [SendTransaction](-send-transaction/index.md) | [androidJvm]<br>data class [SendTransaction](-send-transaction/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val from: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val to: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val nonce: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val gasPrice: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val gasLimit: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val data: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Session.MethodCall](index.md) |
| [SessionRequest](-session-request/index.md) | [androidJvm]<br>data class [SessionRequest](-session-request/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val peer: [Session.PeerData](../-peer-data/index.md)) : [Session.MethodCall](index.md) |
| [SessionUpdate](-session-update/index.md) | [androidJvm]<br>data class [SessionUpdate](-session-update/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val params: [Session.SessionParams](../-session-params/index.md)) : [Session.MethodCall](index.md) |
| [SignMessage](-sign-message/index.md) | [androidJvm]<br>data class [SignMessage](-sign-message/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val address: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Session.MethodCall](index.md) |

## Functions

| Name | Summary |
|---|---|
| [id](id.md) | [androidJvm]<br>fun [id](id.md)(): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |

## Inheritors

| Name |
|---|
| [SessionRequest](-session-request/index.md) |
| [SessionUpdate](-session-update/index.md) |
| [SendTransaction](-send-transaction/index.md) |
| [PersonalSignMessage](-personal-sign-message/index.md) |
| [SignMessage](-sign-message/index.md) |
| [Custom](-custom/index.md) |
| [Response](-response/index.md) |
