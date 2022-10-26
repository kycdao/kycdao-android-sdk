//[app](../../../../index.md)/[com.kycdao.android.sdk.walletconnect.impls](../../index.md)/[WCSessionStore](../index.md)/[State](index.md)

# State

[androidJvm]\
data class [State](index.md)(val config: [Session.Config](../../../com.kycdao.android.sdk.walletconnect/-session/-config/index.md), val clientData: [Session.PeerData](../../../com.kycdao.android.sdk.walletconnect/-session/-peer-data/index.md), val peerData: [Session.PeerData](../../../com.kycdao.android.sdk.walletconnect/-session/-peer-data/index.md)?, val handshakeId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, val currentKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val approvedAccounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, val chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?)

## Constructors

| | |
|---|---|
| [State](-state.md) | [androidJvm]<br>fun [State](-state.md)(config: [Session.Config](../../../com.kycdao.android.sdk.walletconnect/-session/-config/index.md), clientData: [Session.PeerData](../../../com.kycdao.android.sdk.walletconnect/-session/-peer-data/index.md), peerData: [Session.PeerData](../../../com.kycdao.android.sdk.walletconnect/-session/-peer-data/index.md)?, handshakeId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, currentKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), approvedAccounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?) |

## Properties

| Name | Summary |
|---|---|
| [approvedAccounts](approved-accounts.md) | [androidJvm]<br>val [approvedAccounts](approved-accounts.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [chainId](chain-id.md) | [androidJvm]<br>val [chainId](chain-id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [clientData](client-data.md) | [androidJvm]<br>val [clientData](client-data.md): [Session.PeerData](../../../com.kycdao.android.sdk.walletconnect/-session/-peer-data/index.md) |
| [config](config.md) | [androidJvm]<br>val [config](config.md): [Session.Config](../../../com.kycdao.android.sdk.walletconnect/-session/-config/index.md) |
| [currentKey](current-key.md) | [androidJvm]<br>val [currentKey](current-key.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [handshakeId](handshake-id.md) | [androidJvm]<br>val [handshakeId](handshake-id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [peerData](peer-data.md) | [androidJvm]<br>val [peerData](peer-data.md): [Session.PeerData](../../../com.kycdao.android.sdk.walletconnect/-session/-peer-data/index.md)? |
