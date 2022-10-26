//[app](../../../index.md)/[com.kycdao.android.sdk.ui.nftselector](../index.md)/[NftSelectorContract](index.md)

# NftSelectorContract

[androidJvm]\
class [NftSelectorContract](index.md) : [KycActivityResultContract](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/index.md)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../../com.kycdao.android.sdk.model/-available-image/index.md)&gt;, [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

## Constructors

| | |
|---|---|
| [NftSelectorContract](-nft-selector-contract.md) | [androidJvm]<br>fun [NftSelectorContract](-nft-selector-contract.md)() |

## Functions

| Name | Summary |
|---|---|
| [activityClass](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/activity-class.md) | [androidJvm]<br>open fun [activityClass](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/activity-class.md)(): [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[SdkActivity](../../com.kycdao.android.sdk.ui/-sdk-activity/index.md)&gt; |
| [createIntent](index.md#1075659927%2FFunctions%2F-912451524) | [androidJvm]<br>@[CallSuper](https://developer.android.com/reference/kotlin/androidx/annotation/CallSuper.html)<br>open override fun [createIntent](index.md#1075659927%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), input: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../../com.kycdao.android.sdk.model/-available-image/index.md)&gt;): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html) |
| [fragmentFactory](fragment-factory.md) | [androidJvm]<br>open override fun [fragmentFactory](fragment-factory.md)(input: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../../com.kycdao.android.sdk.model/-available-image/index.md)&gt;): [FragmentFactory](../../com.kycdao.android.sdk.ui/-fragment-factory/index.md) |
| [getSynchronousResult](index.md#-1137787404%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getSynchronousResult](index.md#-1137787404%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), input: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../../com.kycdao.android.sdk.model/-available-image/index.md)&gt;): [ActivityResultContract.SynchronousResult](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContract.SynchronousResult.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?&gt;? |
| [parseResult](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/parse-result.md) | [androidJvm]<br>open override fun [parseResult](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/parse-result.md)(resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
