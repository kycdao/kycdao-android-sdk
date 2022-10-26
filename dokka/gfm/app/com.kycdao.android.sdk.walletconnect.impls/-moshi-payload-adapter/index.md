//[app](../../../index.md)/[com.kycdao.android.sdk.walletconnect.impls](../index.md)/[MoshiPayloadAdapter](index.md)

# MoshiPayloadAdapter

[androidJvm]\
class [MoshiPayloadAdapter](index.md)(moshi: Moshi) : [Session.PayloadAdapter](../../com.kycdao.android.sdk.walletconnect/-session/-payload-adapter/index.md)

## Constructors

| | |
|---|---|
| [MoshiPayloadAdapter](-moshi-payload-adapter.md) | [androidJvm]<br>fun [MoshiPayloadAdapter](-moshi-payload-adapter.md)(moshi: Moshi) |

## Types

| Name | Summary |
|---|---|
| [EncryptedPayload](-encrypted-payload/index.md) | [androidJvm]<br>data class [EncryptedPayload](-encrypted-payload/index.md)(@Json(name = &quot;data&quot;)val data: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;iv&quot;)val iv: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;hmac&quot;)val hmac: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | [androidJvm]<br>open override fun [parse](parse.md)(payload: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Session.MethodCall](../../com.kycdao.android.sdk.walletconnect/-session/-method-call/index.md) |
| [prepare](prepare.md) | [androidJvm]<br>open override fun [prepare](prepare.md)(data: [Session.MethodCall](../../com.kycdao.android.sdk.walletconnect/-session/-method-call/index.md), key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
