//[app](../../../index.md)/[com.kycdao.android.sdk.ui.nftselector](../index.md)/[NftSelectorViewModel](index.md)

# NftSelectorViewModel

[androidJvm]\
class [NftSelectorViewModel](index.md)(val list: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../../com.kycdao.android.sdk.model/-available-image/index.md)&gt;) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

## Constructors

| | |
|---|---|
| [NftSelectorViewModel](-nft-selector-view-model.md) | [androidJvm]<br>fun [NftSelectorViewModel](-nft-selector-view-model.md)(list: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../../com.kycdao.android.sdk.model/-available-image/index.md)&gt;) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../../com.kycdao.android.sdk.ui.progress_screen/-progress-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../../com.kycdao.android.sdk.ui.progress_screen/-progress-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [onContinue](on-continue.md) | [androidJvm]<br>fun [onContinue](on-continue.md)() |

## Properties

| Name | Summary |
|---|---|
| [currentItems](current-items.md) | [androidJvm]<br>val [currentItems](current-items.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ListItem](../../com.kycdao.android.sdk.ui.delegate/-list-item/index.md)&gt;&gt; |
| [currentSelectedItem](current-selected-item.md) | [androidJvm]<br>val [currentSelectedItem](current-selected-item.md): [MutableLiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)&lt;[NftItem](../../com.kycdao.android.sdk.ui.nftselector.cell.model/-nft-item/index.md)?&gt; |
| [events](events.md) | [androidJvm]<br>val [events](events.md): [SingleLiveEvent](../../com.kycdao.android.sdk.ui.events/-single-live-event/index.md)&lt;[ViewModelEvent](../../com.kycdao.android.sdk.ui.events/-view-model-event/index.md)&gt; |
| [isButtonEnabled](is-button-enabled.md) | [androidJvm]<br>val [isButtonEnabled](is-button-enabled.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [list](list.md) | [androidJvm]<br>val [list](list.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../../com.kycdao.android.sdk.model/-available-image/index.md)&gt; |
| [outEvents](out-events.md) | [androidJvm]<br>val [outEvents](out-events.md): [SingleLiveEvent](../../com.kycdao.android.sdk.ui.events/-single-live-event/index.md)&lt;[ViewModelEvent](../../com.kycdao.android.sdk.ui.events/-view-model-event/index.md)&gt; |
