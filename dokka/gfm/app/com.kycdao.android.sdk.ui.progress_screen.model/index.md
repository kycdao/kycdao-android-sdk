//[app](../../index.md)/[com.kycdao.android.sdk.ui.progress_screen.model](index.md)

# Package com.kycdao.android.sdk.ui.progress_screen.model

## Types

| Name | Summary |
|---|---|
| [Action](-action/index.md) | [androidJvm]<br>class [Action](-action/index.md)(val label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val function: suspend () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [ActionButton](-action-button/index.md) | [androidJvm]<br>class [ActionButton](-action-button/index.md)(action: [Action](-action/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), attributeSet: [AttributeSet](https://developer.android.com/reference/kotlin/android/util/AttributeSet.html)? = null, theme: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = android.R.style.Widget_Material_Button_Small) : MaterialButton |
| [Progress](-progress/index.md) | [androidJvm]<br>enum [Progress](-progress/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[Progress](-progress/index.md)&gt; |
| [Step](-step/index.md) | [androidJvm]<br>data class [Step](-step/index.md)(val title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), var desc: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, var actions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Action](-action/index.md)&gt; = emptyList(), var progress: [Progress](-progress/index.md) = Progress.TODO, var show: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) : [ListItem](../com.kycdao.android.sdk.ui.delegate/-list-item/index.md) |
