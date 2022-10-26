//[app](../../../index.md)/[com.kycdao.android.sdk.ui.progress_screen.model](../index.md)/[Step](index.md)

# Step

[androidJvm]\
data class [Step](index.md)(val title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), var desc: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, var actions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Action](../-action/index.md)&gt; = emptyList(), var progress: [Progress](../-progress/index.md) = Progress.TODO, var show: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) : [ListItem](../../com.kycdao.android.sdk.ui.delegate/-list-item/index.md)

## Constructors

| | |
|---|---|
| [Step](-step.md) | [androidJvm]<br>fun [Step](-step.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), desc: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, actions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Action](../-action/index.md)&gt; = emptyList(), progress: [Progress](../-progress/index.md) = Progress.TODO, show: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) |

## Functions

| Name | Summary |
|---|---|
| [getAdapterItemHash](../../com.kycdao.android.sdk.ui.delegate/-list-item/get-adapter-item-hash.md) | [androidJvm]<br>open fun [getAdapterItemHash](../../com.kycdao.android.sdk.ui.delegate/-list-item/get-adapter-item-hash.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getAdapterItemId](../../com.kycdao.android.sdk.ui.delegate/-list-item/get-adapter-item-id.md) | [androidJvm]<br>open fun [getAdapterItemId](../../com.kycdao.android.sdk.ui.delegate/-list-item/get-adapter-item-id.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [actions](actions.md) | [androidJvm]<br>var [actions](actions.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Action](../-action/index.md)&gt; |
| [desc](desc.md) | [androidJvm]<br>var [desc](desc.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [progress](progress.md) | [androidJvm]<br>var [progress](progress.md): [Progress](../-progress/index.md) |
| [show](show.md) | [androidJvm]<br>var [show](show.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [title](title.md) | [androidJvm]<br>val [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
