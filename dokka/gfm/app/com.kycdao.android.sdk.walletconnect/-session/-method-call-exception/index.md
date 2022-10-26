//[app](../../../../index.md)/[com.kycdao.android.sdk.walletconnect](../../index.md)/[Session](../index.md)/[MethodCallException](index.md)

# MethodCallException

[androidJvm]\
sealed class [MethodCallException](index.md) : [IllegalArgumentException](https://developer.android.com/reference/kotlin/java/lang/IllegalArgumentException.html)

## Types

| Name | Summary |
|---|---|
| [InvalidAccount](-invalid-account/index.md) | [androidJvm]<br>class [InvalidAccount](-invalid-account/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), account: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Session.MethodCallException](index.md) |
| [InvalidRequest](-invalid-request/index.md) | [androidJvm]<br>class [InvalidRequest](-invalid-request/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), request: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Session.MethodCallException](index.md) |

## Functions

| Name | Summary |
|---|---|
| [addSuppressed](-invalid-account/index.md#282858770%2FFunctions%2F-912451524) | [androidJvm]<br>fun [addSuppressed](-invalid-account/index.md#282858770%2FFunctions%2F-912451524)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [fillInStackTrace](-invalid-account/index.md#-1102069925%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [fillInStackTrace](-invalid-account/index.md#-1102069925%2FFunctions%2F-912451524)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [getLocalizedMessage](-invalid-account/index.md#1043865560%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getLocalizedMessage](-invalid-account/index.md#1043865560%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStackTrace](-invalid-account/index.md#2050903719%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getStackTrace](-invalid-account/index.md#2050903719%2FFunctions%2F-912451524)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt; |
| [getSuppressed](-invalid-account/index.md#672492560%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getSuppressed](-invalid-account/index.md#672492560%2FFunctions%2F-912451524)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)&gt; |
| [initCause](-invalid-account/index.md#-418225042%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [initCause](-invalid-account/index.md#-418225042%2FFunctions%2F-912451524)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [printStackTrace](-invalid-account/index.md#-1769529168%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [printStackTrace](-invalid-account/index.md#-1769529168%2FFunctions%2F-912451524)()<br>open fun [printStackTrace](-invalid-account/index.md#1841853697%2FFunctions%2F-912451524)(p0: [PrintStream](https://developer.android.com/reference/kotlin/java/io/PrintStream.html))<br>open fun [printStackTrace](-invalid-account/index.md#1175535278%2FFunctions%2F-912451524)(p0: [PrintWriter](https://developer.android.com/reference/kotlin/java/io/PrintWriter.html)) |
| [setStackTrace](-invalid-account/index.md#2135801318%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [setStackTrace](-invalid-account/index.md#2135801318%2FFunctions%2F-912451524)(p0: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [cause](-invalid-account/index.md#-654012527%2FProperties%2F-912451524) | [androidJvm]<br>open val [cause](-invalid-account/index.md#-654012527%2FProperties%2F-912451524): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [code](code.md) | [androidJvm]<br>val [code](code.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [message](-invalid-account/index.md#1824300659%2FProperties%2F-912451524) | [androidJvm]<br>open val [message](-invalid-account/index.md#1824300659%2FProperties%2F-912451524): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |

## Inheritors

| Name |
|---|
| [InvalidRequest](-invalid-request/index.md) |
| [InvalidAccount](-invalid-account/index.md) |
