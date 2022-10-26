//[app](../../../index.md)/[com.kycdao.android.sdk.usecase](../index.md)/[GetUserInformationUseCaseImp](index.md)

# GetUserInformationUseCaseImp

[androidJvm]\
class [GetUserInformationUseCaseImp](index.md)(activityResultRegistry: [ActivityResultRegistry](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultRegistry.html), localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), getUserInformationContract: [KycActivityResultContract](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/index.md)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, [PersonalDataResult](../../com.kycdao.android.sdk.model/-personal-data-result/index.md)&gt;) : [GetUserInformationUseCase](../-get-user-information-use-case/index.md)

## Constructors

| | |
|---|---|
| [GetUserInformationUseCaseImp](-get-user-information-use-case-imp.md) | [androidJvm]<br>fun [GetUserInformationUseCaseImp](-get-user-information-use-case-imp.md)(activityResultRegistry: [ActivityResultRegistry](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultRegistry.html), localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), getUserInformationContract: [KycActivityResultContract](../../com.kycdao.android.sdk.ui/-kyc-activity-result-contract/index.md)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, [PersonalDataResult](../../com.kycdao.android.sdk.model/-personal-data-result/index.md)&gt;) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>open suspend operator override fun [invoke](invoke.md)(kycUser: [KycUser](../../com.kycdao.android.sdk.model/-kyc-user/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
