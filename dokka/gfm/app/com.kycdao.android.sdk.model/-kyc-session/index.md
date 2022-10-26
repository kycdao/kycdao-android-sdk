//[app](../../../index.md)/[com.kycdao.android.sdk.model](../index.md)/[KycSession](index.md)

# KycSession

[androidJvm]\
data class [KycSession](index.md)(val walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val network: [Network](../-network/index.md), val kycConfig: [SmartContractConfig](../-smart-contract-config/index.md)?, val accreditedConfig: [SmartContractConfig](../-smart-contract-config/index.md)?, val personaData: [Persona](../-persona/index.md), val sessionData: [SessionData](../-session-data/index.md), val walletConnected: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val signature: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val identityVerificationCompleted: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, var authorizeMintingResponse: [AuthorizeMintingResponse](../../com.kycdao.android.sdk.dto/-authorize-minting-response/index.md)? = null, val authorizeMintingFinished: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val feeCalculationFinished: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val mintTransactionId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val mintTokenId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val mintTokenSent: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)

## Constructors

| | |
|---|---|
| [KycSession](-kyc-session.md) | [androidJvm]<br>fun [KycSession](-kyc-session.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), network: [Network](../-network/index.md), kycConfig: [SmartContractConfig](../-smart-contract-config/index.md)?, accreditedConfig: [SmartContractConfig](../-smart-contract-config/index.md)?, personaData: [Persona](../-persona/index.md), sessionData: [SessionData](../-session-data/index.md), walletConnected: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, signature: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, identityVerificationCompleted: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, authorizeMintingResponse: [AuthorizeMintingResponse](../../com.kycdao.android.sdk.dto/-authorize-minting-response/index.md)? = null, authorizeMintingFinished: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, feeCalculationFinished: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, mintTransactionId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, mintTokenId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, mintTokenSent: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) |

## Functions

| Name | Summary |
|---|---|
| [getState](get-state.md) | [androidJvm]<br>fun [getState](get-state.md)(): [State](../-state/index.md) |
| [hasUserInfo](has-user-info.md) | [androidJvm]<br>fun [hasUserInfo](has-user-info.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDisclaimerAccepted](is-disclaimer-accepted.md) | [androidJvm]<br>fun [isDisclaimerAccepted](is-disclaimer-accepted.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isEmailConfirmed](is-email-confirmed.md) | [androidJvm]<br>fun [isEmailConfirmed](is-email-confirmed.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [loginProof](login-proof.md) | [androidJvm]<br>fun [loginProof](login-proof.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [accreditedConfig](accredited-config.md) | [androidJvm]<br>val [accreditedConfig](accredited-config.md): [SmartContractConfig](../-smart-contract-config/index.md)? |
| [authorizeMintingFinished](authorize-minting-finished.md) | [androidJvm]<br>val [authorizeMintingFinished](authorize-minting-finished.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [authorizeMintingResponse](authorize-minting-response.md) | [androidJvm]<br>var [authorizeMintingResponse](authorize-minting-response.md): [AuthorizeMintingResponse](../../com.kycdao.android.sdk.dto/-authorize-minting-response/index.md)? = null |
| [feeCalculationFinished](fee-calculation-finished.md) | [androidJvm]<br>val [feeCalculationFinished](fee-calculation-finished.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [identityVerificationCompleted](identity-verification-completed.md) | [androidJvm]<br>val [identityVerificationCompleted](identity-verification-completed.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [kycConfig](kyc-config.md) | [androidJvm]<br>val [kycConfig](kyc-config.md): [SmartContractConfig](../-smart-contract-config/index.md)? |
| [mintTokenId](mint-token-id.md) | [androidJvm]<br>val [mintTokenId](mint-token-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [mintTokenSent](mint-token-sent.md) | [androidJvm]<br>val [mintTokenSent](mint-token-sent.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [mintTransactionId](mint-transaction-id.md) | [androidJvm]<br>val [mintTransactionId](mint-transaction-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [network](network.md) | [androidJvm]<br>val [network](network.md): [Network](../-network/index.md) |
| [personaData](persona-data.md) | [androidJvm]<br>val [personaData](persona-data.md): [Persona](../-persona/index.md) |
| [sessionData](session-data.md) | [androidJvm]<br>val [sessionData](session-data.md): [SessionData](../-session-data/index.md) |
| [signature](signature.md) | [androidJvm]<br>val [signature](signature.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [walletAddress](wallet-address.md) | [androidJvm]<br>val [walletAddress](wallet-address.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [walletConnected](wallet-connected.md) | [androidJvm]<br>val [walletConnected](wallet-connected.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
