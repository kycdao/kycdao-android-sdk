//[app](../../../index.md)/[com.kycdao.android.sdk.usecase](../index.md)/[AuthorizeMintingUseCaseImp](index.md)

# AuthorizeMintingUseCaseImp

[androidJvm]\
class [AuthorizeMintingUseCaseImp](index.md)(val localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), val networkDatasource: [NetworkDatasource](../../com.kycdao.android.sdk.network/-network-datasource/index.md)) : [AuthorizeMintingUseCase](../-authorize-minting-use-case/index.md)

## Constructors

| | |
|---|---|
| [AuthorizeMintingUseCaseImp](-authorize-minting-use-case-imp.md) | [androidJvm]<br>fun [AuthorizeMintingUseCaseImp](-authorize-minting-use-case-imp.md)(localDataSource: [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md), networkDatasource: [NetworkDatasource](../../com.kycdao.android.sdk.network/-network-datasource/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>open suspend operator override fun [invoke](invoke.md)(selectedNftId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), kycSession: [KycSession](../../com.kycdao.android.sdk.model/-kyc-session/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [localDataSource](local-data-source.md) | [androidJvm]<br>val [localDataSource](local-data-source.md): [LocalDataSource](../../com.kycdao.android.sdk.db/-local-data-source/index.md) |
| [networkDatasource](network-datasource.md) | [androidJvm]<br>val [networkDatasource](network-datasource.md): [NetworkDatasource](../../com.kycdao.android.sdk.network/-network-datasource/index.md) |
