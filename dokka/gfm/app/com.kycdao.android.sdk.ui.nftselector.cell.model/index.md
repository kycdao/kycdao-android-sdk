//[app](../../index.md)/[com.kycdao.android.sdk.ui.nftselector.cell.model](index.md)

# Package com.kycdao.android.sdk.ui.nftselector.cell.model

## Types

| Name | Summary |
|---|---|
| [NftChoicePage](-nft-choice-page/index.md) | [androidJvm]<br>class [NftChoicePage](-nft-choice-page/index.md)(nftItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RadioButtonModel](-radio-button-model/index.md)&gt;, selected: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?) : [PagerPageModel](-pager-page-model/index.md) |
| [NftItem](-nft-item/index.md) | [androidJvm]<br>data class [NftItem](-nft-item/index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val imageUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val idx: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val isSelected: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [RadioButtonModel](-radio-button-model/index.md) |
| [NftItemSelected](-nft-item-selected/index.md) | [androidJvm]<br>data class [NftItemSelected](-nft-item-selected/index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val item: [NftItem](-nft-item/index.md)) : [ViewModelEvent](../com.kycdao.android.sdk.ui.events/-view-model-event/index.md) |
| [PagerPageModel](-pager-page-model/index.md) | [androidJvm]<br>sealed class [PagerPageModel](-pager-page-model/index.md) |
| [RadioButtonModel](-radio-button-model/index.md) | [androidJvm]<br>sealed class [RadioButtonModel](-radio-button-model/index.md) |
| [RadioButtonView](-radio-button-view/index.md) | [androidJvm]<br>class [RadioButtonView](-radio-button-view/index.md)@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), attrs: [AttributeSet](https://developer.android.com/reference/kotlin/android/util/AttributeSet.html)? = null, defStyleAttr: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0) : ConstraintLayout |
| [RadioGroupModel](-radio-group-model/index.md) | [androidJvm]<br>class [RadioGroupModel](-radio-group-model/index.md)(val choices: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RadioButtonModel](-radio-button-model/index.md)&gt;, val onItemSelected: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), event: [SingleLiveEvent](../com.kycdao.android.sdk.ui.events/-single-live-event/index.md)&lt;[ViewModelEvent](../com.kycdao.android.sdk.ui.events/-view-model-event/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [ListItem](../com.kycdao.android.sdk.ui.delegate/-list-item/index.md) |

## Functions

| Name | Summary |
|---|---|
| [check](check.md) | [androidJvm]<br>fun [RadioButton](https://developer.android.com/reference/kotlin/android/widget/RadioButton.html).[check](check.md)() |
| [unCheck](un-check.md) | [androidJvm]<br>fun [RadioButton](https://developer.android.com/reference/kotlin/android/widget/RadioButton.html).[unCheck](un-check.md)() |
