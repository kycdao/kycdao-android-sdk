//[app](../../../index.md)/[com.kycdao.android.sdk.usecase](../index.md)/[CalculateFeeUseCaseImp](index.md)

# CalculateFeeUseCaseImp

[androidJvm]\
class [CalculateFeeUseCaseImp](index.md)(val estimateGasUseCase: [EstimateGasUseCase](../-estimate-gas-use-case/index.md), val ethGasPriceUseCase: [EthGasPriceUseCase](../-eth-gas-price-use-case/index.md)) : [CalculateFeeUseCase](../-calculate-fee-use-case/index.md)

## Constructors

| | |
|---|---|
| [CalculateFeeUseCaseImp](-calculate-fee-use-case-imp.md) | [androidJvm]<br>fun [CalculateFeeUseCaseImp](-calculate-fee-use-case-imp.md)(estimateGasUseCase: [EstimateGasUseCase](../-estimate-gas-use-case/index.md), ethGasPriceUseCase: [EthGasPriceUseCase](../-eth-gas-price-use-case/index.md)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>open suspend operator override fun [invoke](invoke.md)(): [BigInteger](https://developer.android.com/reference/kotlin/java/math/BigInteger.html) |

## Properties

| Name | Summary |
|---|---|
| [estimateGasUseCase](estimate-gas-use-case.md) | [androidJvm]<br>val [estimateGasUseCase](estimate-gas-use-case.md): [EstimateGasUseCase](../-estimate-gas-use-case/index.md) |
| [ethGasPriceUseCase](eth-gas-price-use-case.md) | [androidJvm]<br>val [ethGasPriceUseCase](eth-gas-price-use-case.md): [EthGasPriceUseCase](../-eth-gas-price-use-case/index.md) |
