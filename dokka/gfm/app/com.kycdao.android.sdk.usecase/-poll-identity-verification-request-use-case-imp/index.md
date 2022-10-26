//[app](../../../index.md)/[com.kycdao.android.sdk.usecase](../index.md)/[PollIdentityVerificationRequestUseCaseImp](index.md)

# PollIdentityVerificationRequestUseCaseImp

[androidJvm]\
class [PollIdentityVerificationRequestUseCaseImp](index.md)(networkDatasource: [NetworkDatasource](../../com.kycdao.android.sdk.network/-network-datasource/index.md), localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)) : [PollIdentityVerificationRequestUseCase](../-poll-identity-verification-request-use-case/index.md)

## Constructors

| | |
|---|---|
| [PollIdentityVerificationRequestUseCaseImp](-poll-identity-verification-request-use-case-imp.md) | [androidJvm]<br>fun [PollIdentityVerificationRequestUseCaseImp](-poll-identity-verification-request-use-case-imp.md)(networkDatasource: [NetworkDatasource](../../com.kycdao.android.sdk.network/-network-datasource/index.md), localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>open operator override fun [invoke](invoke.md)(kycSession: [KycSession](../../com.kycdao.android.sdk.model/-kyc-session/index.md)): Job |
