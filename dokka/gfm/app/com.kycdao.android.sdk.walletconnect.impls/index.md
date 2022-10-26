//[app](../../index.md)/[com.kycdao.android.sdk.walletconnect.impls](index.md)

# Package com.kycdao.android.sdk.walletconnect.impls

## Types

| Name | Summary |
|---|---|
| [FileWCSessionStore](-file-w-c-session-store/index.md) | [androidJvm]<br>class [FileWCSessionStore](-file-w-c-session-store/index.md)(storageFile: [File](https://developer.android.com/reference/kotlin/java/io/File.html), moshi: Moshi) : [WCSessionStore](-w-c-session-store/index.md) |
| [MoshiPayloadAdapter](-moshi-payload-adapter/index.md) | [androidJvm]<br>class [MoshiPayloadAdapter](-moshi-payload-adapter/index.md)(moshi: Moshi) : [Session.PayloadAdapter](../com.kycdao.android.sdk.walletconnect/-session/-payload-adapter/index.md) |
| [OkHttpTransport](-ok-http-transport/index.md) | [androidJvm]<br>class [OkHttpTransport](-ok-http-transport/index.md)(client: OkHttpClient, serverUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), statusHandler: ([Session.Transport.Status](../com.kycdao.android.sdk.walletconnect/-session/-transport/-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), messageHandler: ([Session.Transport.Message](../com.kycdao.android.sdk.walletconnect/-session/-transport/-message/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), moshi: Moshi) : WebSocketListener, [Session.Transport](../com.kycdao.android.sdk.walletconnect/-session/-transport/index.md) |
| [WCSession](-w-c-session/index.md) | [androidJvm]<br>class [WCSession](-w-c-session/index.md)(config: [Session.Config](../com.kycdao.android.sdk.walletconnect/-session/-config/index.md), payloadAdapter: [Session.PayloadAdapter](../com.kycdao.android.sdk.walletconnect/-session/-payload-adapter/index.md), sessionStore: [WCSessionStore](-w-c-session-store/index.md), transportBuilder: [Session.Transport.Builder](../com.kycdao.android.sdk.walletconnect/-session/-transport/-builder/index.md), clientMeta: [Session.PeerMeta](../com.kycdao.android.sdk.walletconnect/-session/-peer-meta/index.md), clientId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [Session](../com.kycdao.android.sdk.walletconnect/-session/index.md) |
| [WCSessionStore](-w-c-session-store/index.md) | [androidJvm]<br>interface [WCSessionStore](-w-c-session-store/index.md) |
