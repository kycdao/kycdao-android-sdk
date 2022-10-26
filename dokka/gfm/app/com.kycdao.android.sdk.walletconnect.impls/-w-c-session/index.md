//[app](../../../index.md)/[com.kycdao.android.sdk.walletconnect.impls](../index.md)/[WCSession](index.md)

# WCSession

[androidJvm]\
class [WCSession](index.md)(config: [Session.Config](../../com.kycdao.android.sdk.walletconnect/-session/-config/index.md), payloadAdapter: [Session.PayloadAdapter](../../com.kycdao.android.sdk.walletconnect/-session/-payload-adapter/index.md), sessionStore: [WCSessionStore](../-w-c-session-store/index.md), transportBuilder: [Session.Transport.Builder](../../com.kycdao.android.sdk.walletconnect/-session/-transport/-builder/index.md), clientMeta: [Session.PeerMeta](../../com.kycdao.android.sdk.walletconnect/-session/-peer-meta/index.md), clientId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [Session](../../com.kycdao.android.sdk.walletconnect/-session/index.md)

## Constructors

| | |
|---|---|
| [WCSession](-w-c-session.md) | [androidJvm]<br>fun [WCSession](-w-c-session.md)(config: [Session.Config](../../com.kycdao.android.sdk.walletconnect/-session/-config/index.md), payloadAdapter: [Session.PayloadAdapter](../../com.kycdao.android.sdk.walletconnect/-session/-payload-adapter/index.md), sessionStore: [WCSessionStore](../-w-c-session-store/index.md), transportBuilder: [Session.Transport.Builder](../../com.kycdao.android.sdk.walletconnect/-session/-transport/-builder/index.md), clientMeta: [Session.PeerMeta](../../com.kycdao.android.sdk.walletconnect/-session/-peer-meta/index.md), clientId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Functions

| Name | Summary |
|---|---|
| [addCallback](add-callback.md) | [androidJvm]<br>open override fun [addCallback](add-callback.md)(cb: [Session.Callback](../../com.kycdao.android.sdk.walletconnect/-session/-callback/index.md)) |
| [approve](approve.md) | [androidJvm]<br>open override fun [approve](approve.md)(accounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) |
| [approvedAccounts](approved-accounts.md) | [androidJvm]<br>open override fun [approvedAccounts](approved-accounts.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [approveRequest](approve-request.md) | [androidJvm]<br>open override fun [approveRequest](approve-request.md)(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), response: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)) |
| [chainId](chain-id.md) | [androidJvm]<br>open override fun [chainId](chain-id.md)(): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [clearCallbacks](clear-callbacks.md) | [androidJvm]<br>open override fun [clearCallbacks](clear-callbacks.md)() |
| [init](init.md) | [androidJvm]<br>open override fun [init](init.md)() |
| [kill](kill.md) | [androidJvm]<br>open override fun [kill](kill.md)() |
| [offer](offer.md) | [androidJvm]<br>open override fun [offer](offer.md)()<br>Send client info to the bridge and wait for a client to connect |
| [peerMeta](peer-meta.md) | [androidJvm]<br>open override fun [peerMeta](peer-meta.md)(): [Session.PeerMeta](../../com.kycdao.android.sdk.walletconnect/-session/-peer-meta/index.md)? |
| [performMethodCall](perform-method-call.md) | [androidJvm]<br>open override fun [performMethodCall](perform-method-call.md)(call: [Session.MethodCall](../../com.kycdao.android.sdk.walletconnect/-session/-method-call/index.md), callback: ([Session.MethodCall.Response](../../com.kycdao.android.sdk.walletconnect/-session/-method-call/-response/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) |
| [reject](reject.md) | [androidJvm]<br>open override fun [reject](reject.md)() |
| [rejectRequest](reject-request.md) | [androidJvm]<br>open override fun [rejectRequest](reject-request.md)(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), errorCode: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), errorMsg: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [removeCallback](remove-callback.md) | [androidJvm]<br>open override fun [removeCallback](remove-callback.md)(cb: [Session.Callback](../../com.kycdao.android.sdk.walletconnect/-session/-callback/index.md)) |
| [update](update.md) | [androidJvm]<br>open override fun [update](update.md)(accounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, chainId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) |
