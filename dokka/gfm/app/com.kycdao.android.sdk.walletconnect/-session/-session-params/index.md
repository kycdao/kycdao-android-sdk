//[app](../../../../index.md)/[com.kycdao.android.sdk.walletconnect](../../index.md)/[Session](../index.md)/[SessionParams](index.md)

# SessionParams

[androidJvm]\
data class [SessionParams](index.md)(val approved: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, val accounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, val peerData: [Session.PeerData](../-peer-data/index.md)?)

## Constructors

| | |
|---|---|
| [SessionParams](-session-params.md) | [androidJvm]<br>fun [SessionParams](-session-params.md)(approved: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, accounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, peerData: [Session.PeerData](../-peer-data/index.md)?) |

## Properties

| Name | Summary |
|---|---|
| [accounts](accounts.md) | [androidJvm]<br>val [accounts](accounts.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [approved](approved.md) | [androidJvm]<br>val [approved](approved.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [chainId](chain-id.md) | [androidJvm]<br>val [chainId](chain-id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [peerData](peer-data.md) | [androidJvm]<br>val [peerData](peer-data.md): [Session.PeerData](../-peer-data/index.md)? |

## Extensions

| Name | Summary |
|---|---|
| [intoMap](../../../com.kycdao.android.sdk.walletconnect.types/into-map.md) | [androidJvm]<br>fun [Session.SessionParams](index.md).[intoMap](../../../com.kycdao.android.sdk.walletconnect.types/into-map.md)(params: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt; = mutableMapOf()): [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt; |
