//[app](../../../index.md)/[com.kycdao.android.sdk.usecase](../index.md)/[PollEmailConfirmedUseCaseImp](index.md)

# PollEmailConfirmedUseCaseImp

[androidJvm]\
class [PollEmailConfirmedUseCaseImp](index.md)(networkDatasource: [NetworkDatasource](../../com.kycdao.android.sdk.network/-network-datasource/index.md), localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)) : [PollEmailConfirmedUseCase](../-poll-email-confirmed-use-case/index.md)

## Constructors

| | |
|---|---|
| [PollEmailConfirmedUseCaseImp](-poll-email-confirmed-use-case-imp.md) | [androidJvm]<br>fun [PollEmailConfirmedUseCaseImp](-poll-email-confirmed-use-case-imp.md)(networkDatasource: [NetworkDatasource](../../com.kycdao.android.sdk.network/-network-datasource/index.md), localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>open operator override fun [invoke](invoke.md)(kycUser: [KycUser](../../com.kycdao.android.sdk.model/-kyc-user/index.md)): Job |
