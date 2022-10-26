//[app](../../../index.md)/[com.kycdao.android.sdk.walletconnect.impls](../index.md)/[FileWCSessionStore](index.md)

# FileWCSessionStore

[androidJvm]\
class [FileWCSessionStore](index.md)(storageFile: [File](https://developer.android.com/reference/kotlin/java/io/File.html), moshi: Moshi) : [WCSessionStore](../-w-c-session-store/index.md)

## Constructors

| | |
|---|---|
| [FileWCSessionStore](-file-w-c-session-store.md) | [androidJvm]<br>fun [FileWCSessionStore](-file-w-c-session-store.md)(storageFile: [File](https://developer.android.com/reference/kotlin/java/io/File.html), moshi: Moshi) |

## Functions

| Name | Summary |
|---|---|
| [list](list.md) | [androidJvm]<br>open override fun [list](list.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[WCSessionStore.State](../-w-c-session-store/-state/index.md)&gt; |
| [load](load.md) | [androidJvm]<br>open override fun [load](load.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [WCSessionStore.State](../-w-c-session-store/-state/index.md)? |
| [remove](remove.md) | [androidJvm]<br>open override fun [remove](remove.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [store](store.md) | [androidJvm]<br>open override fun [store](store.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), state: [WCSessionStore.State](../-w-c-session-store/-state/index.md)) |
