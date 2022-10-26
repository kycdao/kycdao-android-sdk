//[app](../../../index.md)/[com.kycdao.android.sdk.walletconnect.impls](../index.md)/[WCSessionStore](index.md)

# WCSessionStore

[androidJvm]\
interface [WCSessionStore](index.md)

## Types

| Name | Summary |
|---|---|
| [State](-state/index.md) | [androidJvm]<br>data class [State](-state/index.md)(val config: [Session.Config](../../com.kycdao.android.sdk.walletconnect/-session/-config/index.md), val clientData: [Session.PeerData](../../com.kycdao.android.sdk.walletconnect/-session/-peer-data/index.md), val peerData: [Session.PeerData](../../com.kycdao.android.sdk.walletconnect/-session/-peer-data/index.md)?, val handshakeId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, val currentKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val approvedAccounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, val chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?) |

## Functions

| Name | Summary |
|---|---|
| [list](list.md) | [androidJvm]<br>abstract fun [list](list.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[WCSessionStore.State](-state/index.md)&gt; |
| [load](load.md) | [androidJvm]<br>abstract fun [load](load.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [WCSessionStore.State](-state/index.md)? |
| [remove](remove.md) | [androidJvm]<br>abstract fun [remove](remove.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [store](store.md) | [androidJvm]<br>abstract fun [store](store.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), state: [WCSessionStore.State](-state/index.md)) |

## Inheritors

| Name |
|---|
| [FileWCSessionStore](../-file-w-c-session-store/index.md) |
