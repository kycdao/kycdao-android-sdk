//[app](../../../../index.md)/[com.kycdao.android.sdk.walletconnect](../../index.md)/[Session](../index.md)/[Config](index.md)

# Config

[androidJvm]\
data class [Config](index.md)(val handshakeTopic: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bridge: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val protocol: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;wc&quot;, val version: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1)

## Constructors

| | |
|---|---|
| [Config](-config.md) | [androidJvm]<br>fun [Config](-config.md)(handshakeTopic: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bridge: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, protocol: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;wc&quot;, version: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [isFullyQualifiedConfig](is-fully-qualified-config.md) | [androidJvm]<br>fun [isFullyQualifiedConfig](is-fully-qualified-config.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toFullyQualifiedConfig](to-fully-qualified-config.md) | [androidJvm]<br>fun [toFullyQualifiedConfig](to-fully-qualified-config.md)(): [Session.FullyQualifiedConfig](../-fully-qualified-config/index.md) |
| [toWCUri](to-w-c-uri.md) | [androidJvm]<br>fun [toWCUri](to-w-c-uri.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [bridge](bridge.md) | [androidJvm]<br>val [bridge](bridge.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [handshakeTopic](handshake-topic.md) | [androidJvm]<br>val [handshakeTopic](handshake-topic.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [key](key.md) | [androidJvm]<br>val [key](key.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [protocol](protocol.md) | [androidJvm]<br>val [protocol](protocol.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [version](version.md) | [androidJvm]<br>val [version](version.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1 |
