//[app](../../../../index.md)/[com.kycdao.android.sdk.walletconnect](../../index.md)/[Session](../index.md)/[PayloadAdapter](index.md)

# PayloadAdapter

[androidJvm]\
interface [PayloadAdapter](index.md)

## Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | [androidJvm]<br>abstract fun [parse](parse.md)(payload: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Session.MethodCall](../-method-call/index.md) |
| [prepare](prepare.md) | [androidJvm]<br>abstract fun [prepare](prepare.md)(data: [Session.MethodCall](../-method-call/index.md), key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [MoshiPayloadAdapter](../../../com.kycdao.android.sdk.walletconnect.impls/-moshi-payload-adapter/index.md) |
