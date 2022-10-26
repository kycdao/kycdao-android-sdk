//[app](../../../index.md)/[com.kycdao.android.sdk.ui](../index.md)/[KycActivityResultContract](index.md)

# KycActivityResultContract

[androidJvm]\
abstract class [KycActivityResultContract](index.md)&lt;[Input](index.md), [Result](index.md) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html)&gt; : [ActivityResultContract](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContract.html)&lt;[Input](index.md), [Result](index.md)?&gt;

## Constructors

| | |
|---|---|
| [KycActivityResultContract](-kyc-activity-result-contract.md) | [androidJvm]<br>fun [KycActivityResultContract](-kyc-activity-result-contract.md)() |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [activityClass](activity-class.md) | [androidJvm]<br>open fun [activityClass](activity-class.md)(): [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[SdkActivity](../-sdk-activity/index.md)&gt; |
| [createIntent](create-intent.md) | [androidJvm]<br>@[CallSuper](https://developer.android.com/reference/kotlin/androidx/annotation/CallSuper.html)<br>open override fun [createIntent](create-intent.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), input: [Input](index.md)): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html) |
| [fragmentFactory](fragment-factory.md) | [androidJvm]<br>abstract fun [fragmentFactory](fragment-factory.md)(input: [Input](index.md)): [FragmentFactory](../-fragment-factory/index.md) |
| [getSynchronousResult](index.md#-456441116%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getSynchronousResult](index.md#-456441116%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), input: [Input](index.md)): [ActivityResultContract.SynchronousResult](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContract.SynchronousResult.html)&lt;[Result](index.md)?&gt;? |
| [parseResult](parse-result.md) | [androidJvm]<br>open override fun [parseResult](parse-result.md)(resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?): [Result](index.md)? |

## Inheritors

| Name |
|---|
| [NftSelectorContract](../../com.kycdao.android.sdk.ui.nftselector/-nft-selector-contract/index.md) |
| [GetUserInformationContract](../../com.kycdao.android.sdk.ui.personaldataform/-get-user-information-contract/index.md) |
