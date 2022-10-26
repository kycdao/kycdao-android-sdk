//[app](../../../index.md)/[com.kycdao.android.sdk.usecase](../index.md)/[ConnectUseCaseImp](index.md)

# ConnectUseCaseImp

[androidJvm]\
class [ConnectUseCaseImp](index.md)(val createWCSessionUseCase: [CreateWCSessionUseCase](../-create-w-c-session-use-case/index.md), val localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), val walletIntent: [WalletIntent](../-wallet-intent/index.md)) : [ConnectUseCase](../-connect-use-case/index.md)

## Constructors

| | |
|---|---|
| [ConnectUseCaseImp](-connect-use-case-imp.md) | [androidJvm]<br>fun [ConnectUseCaseImp](-connect-use-case-imp.md)(createWCSessionUseCase: [CreateWCSessionUseCase](../-create-w-c-session-use-case/index.md), localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), walletIntent: [WalletIntent](../-wallet-intent/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>open suspend operator override fun [invoke](invoke.md)(): [_WCSession](../../com.kycdao.android.sdk.wcsession/_-w-c-session/index.md) |

## Properties

| Name | Summary |
|---|---|
| [createWCSessionUseCase](create-w-c-session-use-case.md) | [androidJvm]<br>val [createWCSessionUseCase](create-w-c-session-use-case.md): [CreateWCSessionUseCase](../-create-w-c-session-use-case/index.md) |
| [localDataSource](local-data-source.md) | [androidJvm]<br>val [localDataSource](local-data-source.md): [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md) |
| [walletIntent](wallet-intent.md) | [androidJvm]<br>val [walletIntent](wallet-intent.md): [WalletIntent](../-wallet-intent/index.md) |
