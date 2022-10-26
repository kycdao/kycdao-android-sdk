//[app](../../../index.md)/[com.kycdao.android.sdk.server](../index.md)/[BridgeServer](index.md)

# BridgeServer

[androidJvm]\
class [BridgeServer](index.md)(moshi: Moshi) : WebSocketServer

## Constructors

| | |
|---|---|
| [BridgeServer](-bridge-server.md) | [androidJvm]<br>fun [BridgeServer](-bridge-server.md)(moshi: Moshi) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [broadcast](index.md#-743565119%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [broadcast](index.md#-743565119%2FFunctions%2F-912451524)(p0: [ByteBuffer](https://developer.android.com/reference/kotlin/java/nio/ByteBuffer.html))<br>open fun [broadcast](index.md#1500833167%2FFunctions%2F-912451524)(p0: [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html))<br>open fun [broadcast](index.md#567854193%2FFunctions%2F-912451524)(p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>open fun [broadcast](index.md#1430689995%2FFunctions%2F-912451524)(p0: [ByteBuffer](https://developer.android.com/reference/kotlin/java/nio/ByteBuffer.html), p1: [MutableCollection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-collection/index.html)&lt;WebSocket&gt;)<br>open fun [broadcast](index.md#1734172989%2FFunctions%2F-912451524)(p0: [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html), p1: [MutableCollection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-collection/index.html)&lt;WebSocket&gt;)<br>open fun [broadcast](index.md#-1307734245%2FFunctions%2F-912451524)(p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p1: [MutableCollection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-collection/index.html)&lt;WebSocket&gt;) |
| [createBuffer](index.md#1477943850%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [createBuffer](index.md#1477943850%2FFunctions%2F-912451524)(): [ByteBuffer](https://developer.android.com/reference/kotlin/java/nio/ByteBuffer.html) |
| [getDraft](index.md#-1875455493%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getDraft](index.md#-1875455493%2FFunctions%2F-912451524)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;Draft&gt; |
| [getLocalSocketAddress](index.md#-634403045%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [getLocalSocketAddress](index.md#-634403045%2FFunctions%2F-912451524)(p0: WebSocket): [InetSocketAddress](https://developer.android.com/reference/kotlin/java/net/InetSocketAddress.html) |
| [getPort](index.md#-1819797059%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getPort](index.md#-1819797059%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getRemoteSocketAddress](index.md#-1301857004%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [getRemoteSocketAddress](index.md#-1301857004%2FFunctions%2F-912451524)(p0: WebSocket): [InetSocketAddress](https://developer.android.com/reference/kotlin/java/net/InetSocketAddress.html) |
| [getWebSocketFactory](index.md#-722230309%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getWebSocketFactory](index.md#-722230309%2FFunctions%2F-912451524)(): WebSocketFactory |
| [onClose](on-close.md) | [androidJvm]<br>open override fun [onClose](on-close.md)(conn: WebSocket?, code: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), reason: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, remote: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [onCloseInitiated](index.md#-455075365%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onCloseInitiated](index.md#-455075365%2FFunctions%2F-912451524)(p0: WebSocket, p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p2: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [onClosing](index.md#-263582189%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onClosing](index.md#-263582189%2FFunctions%2F-912451524)(p0: WebSocket, p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p2: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p3: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [onError](on-error.md) | [androidJvm]<br>open override fun [onError](on-error.md)(conn: WebSocket?, ex: [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)?) |
| [onMessage](on-message.md) | [androidJvm]<br>open override fun [onMessage](on-message.md)(conn: WebSocket?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)<br>open fun [onMessage](index.md#76754932%2FFunctions%2F-912451524)(p0: WebSocket, p1: [ByteBuffer](https://developer.android.com/reference/kotlin/java/nio/ByteBuffer.html)) |
| [onOpen](on-open.md) | [androidJvm]<br>open override fun [onOpen](on-open.md)(conn: WebSocket?, handshake: ClientHandshake?) |
| [onStart](on-start.md) | [androidJvm]<br>open override fun [onStart](on-start.md)() |
| [onWebsocketClose](index.md#-644765589%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [onWebsocketClose](index.md#-644765589%2FFunctions%2F-912451524)(p0: WebSocket, p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p2: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p3: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [onWebsocketCloseInitiated](index.md#-1765491370%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onWebsocketCloseInitiated](index.md#-1765491370%2FFunctions%2F-912451524)(p0: WebSocket, p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p2: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [onWebsocketClosing](index.md#1304233614%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onWebsocketClosing](index.md#1304233614%2FFunctions%2F-912451524)(p0: WebSocket, p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p2: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p3: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [onWebsocketError](index.md#1507287073%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [onWebsocketError](index.md#1507287073%2FFunctions%2F-912451524)(p0: WebSocket, p1: [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)) |
| [onWebsocketHandshakeReceivedAsClient](index.md#-1029655825%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onWebsocketHandshakeReceivedAsClient](index.md#-1029655825%2FFunctions%2F-912451524)(p0: WebSocket, p1: ClientHandshake, p2: ServerHandshake) |
| [onWebsocketHandshakeReceivedAsServer](index.md#-923499615%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onWebsocketHandshakeReceivedAsServer](index.md#-923499615%2FFunctions%2F-912451524)(p0: WebSocket, p1: Draft, p2: ClientHandshake): ServerHandshakeBuilder |
| [onWebsocketHandshakeSentAsClient](index.md#-1512341980%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onWebsocketHandshakeSentAsClient](index.md#-1512341980%2FFunctions%2F-912451524)(p0: WebSocket, p1: ClientHandshake) |
| [onWebsocketMessage](index.md#-1148390417%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [onWebsocketMessage](index.md#-1148390417%2FFunctions%2F-912451524)(p0: WebSocket, p1: [ByteBuffer](https://developer.android.com/reference/kotlin/java/nio/ByteBuffer.html))<br>override fun [onWebsocketMessage](index.md#-1542514145%2FFunctions%2F-912451524)(p0: WebSocket, p1: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [onWebsocketOpen](index.md#-613426950%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [onWebsocketOpen](index.md#-613426950%2FFunctions%2F-912451524)(p0: WebSocket, p1: Handshakedata) |
| [onWebsocketPing](index.md#377383492%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onWebsocketPing](index.md#377383492%2FFunctions%2F-912451524)(p0: WebSocket, p1: Framedata) |
| [onWebsocketPong](index.md#2021823818%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onWebsocketPong](index.md#2021823818%2FFunctions%2F-912451524)(p0: WebSocket, p1: Framedata) |
| [onWriteDemand](index.md#-1519751908%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [onWriteDemand](index.md#-1519751908%2FFunctions%2F-912451524)(p0: WebSocket) |
| [run](index.md#179548713%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [run](index.md#179548713%2FFunctions%2F-912451524)() |
| [setWebSocketFactory](index.md#-840245351%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setWebSocketFactory](index.md#-840245351%2FFunctions%2F-912451524)(p0: WebSocketServerFactory) |
| [start](index.md#1027362418%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [start](index.md#1027362418%2FFunctions%2F-912451524)() |
| [stop](index.md#358723140%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [stop](index.md#358723140%2FFunctions%2F-912451524)()<br>open fun [stop](index.md#1300603618%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [address](index.md#-1902320288%2FProperties%2F-912451524) | [androidJvm]<br>val [address](index.md#-1902320288%2FProperties%2F-912451524): [InetSocketAddress](https://developer.android.com/reference/kotlin/java/net/InetSocketAddress.html) |
| [connectionLostTimeout](index.md#147305155%2FProperties%2F-912451524) | [androidJvm]<br>var [connectionLostTimeout](index.md#147305155%2FProperties%2F-912451524): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [connections](index.md#-2068357217%2FProperties%2F-912451524) | [androidJvm]<br>val [connections](index.md#-2068357217%2FProperties%2F-912451524): [MutableCollection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-collection/index.html)&lt;WebSocket&gt; |
| [reuseAddr](index.md#1168686173%2FProperties%2F-912451524) | [androidJvm]<br>var [reuseAddr](index.md#1168686173%2FProperties%2F-912451524): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [tcpNoDelay](index.md#-1145581353%2FProperties%2F-912451524) | [androidJvm]<br>var [tcpNoDelay](index.md#-1145581353%2FProperties%2F-912451524): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
