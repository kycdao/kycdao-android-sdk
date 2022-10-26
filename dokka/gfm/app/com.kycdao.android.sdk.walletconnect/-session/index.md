//[app](../../../index.md)/[com.kycdao.android.sdk.walletconnect](../index.md)/[Session](index.md)

# Session

[androidJvm]\
interface [Session](index.md)

## Types

| Name | Summary |
|---|---|
| [Callback](-callback/index.md) | [androidJvm]<br>interface [Callback](-callback/index.md) |
| [Config](-config/index.md) | [androidJvm]<br>data class [Config](-config/index.md)(val handshakeTopic: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bridge: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val protocol: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;wc&quot;, val version: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1) |
| [Error](-error/index.md) | [androidJvm]<br>data class [Error](-error/index.md)(val code: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [FullyQualifiedConfig](-fully-qualified-config/index.md) | [androidJvm]<br>data class [FullyQualifiedConfig](-fully-qualified-config/index.md)(val handshakeTopic: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bridge: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val protocol: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;wc&quot;, val version: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1) |
| [MethodCall](-method-call/index.md) | [androidJvm]<br>sealed class [MethodCall](-method-call/index.md) |
| [MethodCallException](-method-call-exception/index.md) | [androidJvm]<br>sealed class [MethodCallException](-method-call-exception/index.md) : [IllegalArgumentException](https://developer.android.com/reference/kotlin/java/lang/IllegalArgumentException.html) |
| [PayloadAdapter](-payload-adapter/index.md) | [androidJvm]<br>interface [PayloadAdapter](-payload-adapter/index.md) |
| [PeerData](-peer-data/index.md) | [androidJvm]<br>data class [PeerData](-peer-data/index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val meta: [Session.PeerMeta](-peer-meta/index.md)?) |
| [PeerMeta](-peer-meta/index.md) | [androidJvm]<br>data class [PeerMeta](-peer-meta/index.md)(val url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val icons: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? = null) |
| [SessionParams](-session-params/index.md) | [androidJvm]<br>data class [SessionParams](-session-params/index.md)(val approved: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, val accounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, val peerData: [Session.PeerData](-peer-data/index.md)?) |
| [Status](-status/index.md) | [androidJvm]<br>sealed class [Status](-status/index.md) |
| [Transport](-transport/index.md) | [androidJvm]<br>interface [Transport](-transport/index.md) |
| [TransportError](-transport-error/index.md) | [androidJvm]<br>data class [TransportError](-transport-error/index.md)(val cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [RuntimeException](https://developer.android.com/reference/kotlin/java/lang/RuntimeException.html) |

## Functions

| Name | Summary |
|---|---|
| [addCallback](add-callback.md) | [androidJvm]<br>abstract fun [addCallback](add-callback.md)(cb: [Session.Callback](-callback/index.md)) |
| [approve](approve.md) | [androidJvm]<br>abstract fun [approve](approve.md)(accounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) |
| [approvedAccounts](approved-accounts.md) | [androidJvm]<br>abstract fun [approvedAccounts](approved-accounts.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [approveRequest](approve-request.md) | [androidJvm]<br>abstract fun [approveRequest](approve-request.md)(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), response: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)) |
| [chainId](chain-id.md) | [androidJvm]<br>abstract fun [chainId](chain-id.md)(): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [clearCallbacks](clear-callbacks.md) | [androidJvm]<br>abstract fun [clearCallbacks](clear-callbacks.md)() |
| [init](init.md) | [androidJvm]<br>abstract fun [init](init.md)() |
| [kill](kill.md) | [androidJvm]<br>abstract fun [kill](kill.md)() |
| [offer](offer.md) | [androidJvm]<br>abstract fun [offer](offer.md)()<br>Send client info to the bridge and wait for a client to connect |
| [peerMeta](peer-meta.md) | [androidJvm]<br>abstract fun [peerMeta](peer-meta.md)(): [Session.PeerMeta](-peer-meta/index.md)? |
| [performMethodCall](perform-method-call.md) | [androidJvm]<br>abstract fun [performMethodCall](perform-method-call.md)(call: [Session.MethodCall](-method-call/index.md), callback: ([Session.MethodCall.Response](-method-call/-response/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |
| [reject](reject.md) | [androidJvm]<br>abstract fun [reject](reject.md)() |
| [rejectRequest](reject-request.md) | [androidJvm]<br>abstract fun [rejectRequest](reject-request.md)(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), errorCode: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), errorMsg: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [removeCallback](remove-callback.md) | [androidJvm]<br>abstract fun [removeCallback](remove-callback.md)(cb: [Session.Callback](-callback/index.md)) |
| [update](update.md) | [androidJvm]<br>abstract fun [update](update.md)(accounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) |

## Inheritors

| Name |
|---|
| [WCSession](../../com.kycdao.android.sdk.walletconnect.impls/-w-c-session/index.md) |
