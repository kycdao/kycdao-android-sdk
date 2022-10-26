//[app](../../../index.md)/[com.kycdao.android.sdk.ui.personaldataform](../index.md)/[GetUserInformationContract](index.md)

# GetUserInformationContract

[androidJvm]\
class [GetUserInformationContract](index.md) : [KycActivityResultContract](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/index.md)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, [PersonalDataResult](../../com.kycdao.android.sdk.model/-personal-data-result/index.md)&gt;

## Constructors

| | |
|---|---|
| [GetUserInformationContract](-get-user-information-contract.md) | [androidJvm]<br>fun [GetUserInformationContract](-get-user-information-contract.md)() |

## Functions

| Name | Summary |
|---|---|
| [activityClass](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/activity-class.md) | [androidJvm]<br>open fun [activityClass](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/activity-class.md)(): [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[SdkActivity](../../com.kycdao.android.sdk.ui/-sdk-activity/index.md)&gt; |
| [createIntent](index.md#-1163062299%2FFunctions%2F-912451524) | [androidJvm]<br>@[CallSuper](https://developer.android.com/reference/kotlin/androidx/annotation/CallSuper.html)<br>open override fun [createIntent](index.md#-1163062299%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), input: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html) |
| [fragmentFactory](fragment-factory.md) | [androidJvm]<br>open override fun [fragmentFactory](fragment-factory.md)(input: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [FragmentFactory](../../com.kycdao.android.sdk.ui/-fragment-factory/index.md) |
| [getSynchronousResult](index.md#1000366056%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getSynchronousResult](index.md#1000366056%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), input: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [ActivityResultContract.SynchronousResult](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContract.SynchronousResult.html)&lt;[PersonalDataResult](../../com.kycdao.android.sdk.model/-personal-data-result/index.md)?&gt;? |
| [parseResult](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/parse-result.md) | [androidJvm]<br>open override fun [parseResult](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/parse-result.md)(resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?): [PersonalDataResult](../../com.kycdao.android.sdk.model/-personal-data-result/index.md)? |
