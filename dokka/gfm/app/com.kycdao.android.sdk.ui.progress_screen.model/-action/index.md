//[app](../../../index.md)/[com.kycdao.android.sdk.ui.progress_screen.model](../index.md)/[Action](index.md)

# Action

[androidJvm]\
class [Action](index.md)(val label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val function: suspend () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

## Constructors

| | |
|---|---|
| [Action](-action.md) | [androidJvm]<br>fun [Action](-action.md)(label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), function: suspend () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [getButton](get-button.md) | [androidJvm]<br>fun [getButton](get-button.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [ActionButton](../-action-button/index.md) |
| [getLayoutParams](get-layout-params.md) | [androidJvm]<br>fun [getLayoutParams](get-layout-params.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): ConstraintLayout.LayoutParams |

## Properties

| Name | Summary |
|---|---|
| [enabled](enabled.md) | [androidJvm]<br>val [enabled](enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [function](function.md) | [androidJvm]<br>val [function](function.md): suspend () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [label](label.md) | [androidJvm]<br>val [label](label.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
