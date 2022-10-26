//[app](../../../../index.md)/[com.kycdao.android.sdk.walletconnect.impls](../../index.md)/[OkHttpTransport](../index.md)/[Builder](index.md)

# Builder

[androidJvm]\
class [Builder](index.md)(val client: OkHttpClient, val moshi: Moshi) : [Session.Transport.Builder](../../../com.kycdao.android.sdk.walletconnect/-session/-transport/-builder/index.md)

## Constructors

| | |
|---|---|
| [Builder](-builder.md) | [androidJvm]<br>fun [Builder](-builder.md)(client: OkHttpClient, moshi: Moshi) |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [androidJvm]<br>open override fun [build](build.md)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), statusHandler: ([Session.Transport.Status](../../../com.kycdao.android.sdk.walletconnect/-session/-transport/-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), messageHandler: ([Session.Transport.Message](../../../com.kycdao.android.sdk.walletconnect/-session/-transport/-message/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Session.Transport](../../../com.kycdao.android.sdk.walletconnect/-session/-transport/index.md) |

## Properties

| Name | Summary |
|---|---|
| [client](client.md) | [androidJvm]<br>val [client](client.md): OkHttpClient |
| [moshi](moshi.md) | [androidJvm]<br>val [moshi](moshi.md): Moshi |
