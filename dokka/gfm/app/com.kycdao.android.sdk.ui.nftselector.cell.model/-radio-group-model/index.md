//[app](../../../index.md)/[com.kycdao.android.sdk.ui.nftselector.cell.model](../index.md)/[RadioGroupModel](index.md)

# RadioGroupModel

[androidJvm]\
class [RadioGroupModel](index.md)(val choices: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RadioButtonModel](../-radio-button-model/index.md)&gt;, val onItemSelected: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), event: [SingleLiveEvent](../../com.kycdao.android.sdk.ui.events/-single-live-event/index.md)&lt;[ViewModelEvent](../../com.kycdao.android.sdk.ui.events/-view-model-event/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [ListItem](../../com.kycdao.android.sdk.ui.delegate/-list-item/index.md)

## Constructors

| | |
|---|---|
| [RadioGroupModel](-radio-group-model.md) | [androidJvm]<br>fun [RadioGroupModel](-radio-group-model.md)(choices: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RadioButtonModel](../-radio-button-model/index.md)&gt;, onItemSelected: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), event: [SingleLiveEvent](../../com.kycdao.android.sdk.ui.events/-single-live-event/index.md)&lt;[ViewModelEvent](../../com.kycdao.android.sdk.ui.events/-view-model-event/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [getAdapterItemHash](get-adapter-item-hash.md) | [androidJvm]<br>open override fun [getAdapterItemHash](get-adapter-item-hash.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getAdapterItemId](../../com.kycdao.android.sdk.ui.delegate/-list-item/get-adapter-item-id.md) | [androidJvm]<br>open fun [getAdapterItemId](../../com.kycdao.android.sdk.ui.delegate/-list-item/get-adapter-item-id.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [choices](choices.md) | [androidJvm]<br>val [choices](choices.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RadioButtonModel](../-radio-button-model/index.md)&gt; |
| [onItemSelected](on-item-selected.md) | [androidJvm]<br>val [onItemSelected](on-item-selected.md): ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), event: [SingleLiveEvent](../../com.kycdao.android.sdk.ui.events/-single-live-event/index.md)&lt;[ViewModelEvent](../../com.kycdao.android.sdk.ui.events/-view-model-event/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
