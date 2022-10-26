//[app](../../../index.md)/[com.kycdao.android.sdk.kycSession](../index.md)/[KycSession](index.md)

# KycSession

[androidJvm]\
data class [KycSession](index.md)(val walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), network: [Network](../../com.kycdao.android.sdk.model/-network/index.md), kycConfig: [SmartContractConfig](../../com.kycdao.android.sdk.model/-smart-contract-config/index.md)?, accreditedConfig: [SmartContractConfig](../../com.kycdao.android.sdk.model/-smart-contract-config/index.md)?, personaData: [Persona](../../com.kycdao.android.sdk.model/-persona/index.md), sessionData: [SessionData](../../com.kycdao.android.sdk.model/-session-data/index.md), val walletSession: [WalletSession](../../com.kycdao.android.sdk.wallet/-wallet-session/index.md), authorizeMintingResponse: [AuthorizeMintingResponse](../../com.kycdao.android.sdk.dto/-authorize-minting-response/index.md)? = null)

- 

A KYC session object which contains session related data and session related operations

An instance should be created by calling the KycManager-s createSession function.

## Constructors

| | |
|---|---|
| [KycSession](-kyc-session.md) | [androidJvm]<br>fun [KycSession](-kyc-session.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), network: [Network](../../com.kycdao.android.sdk.model/-network/index.md), kycConfig: [SmartContractConfig](../../com.kycdao.android.sdk.model/-smart-contract-config/index.md)?, accreditedConfig: [SmartContractConfig](../../com.kycdao.android.sdk.model/-smart-contract-config/index.md)?, personaData: [Persona](../../com.kycdao.android.sdk.model/-persona/index.md), sessionData: [SessionData](../../com.kycdao.android.sdk.model/-session-data/index.md), walletSession: [WalletSession](../../com.kycdao.android.sdk.wallet/-wallet-session/index.md), authorizeMintingResponse: [AuthorizeMintingResponse](../../com.kycdao.android.sdk.dto/-authorize-minting-response/index.md)? = null) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [acceptDisclaimer](accept-disclaimer.md) | [androidJvm]<br>suspend fun [acceptDisclaimer](accept-disclaimer.md)()<br>Accepts the disclaimer if the disclaimer hasn't benn accepted before. |
| [checkHasValidToken](check-has-valid-token.md) | [androidJvm]<br>suspend fun [checkHasValidToken](check-has-valid-token.md)(verificationType: [VerificationType](../../com.kycdao.android.sdk.model/-verification-type/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [estimateGasForMinting](estimate-gas-for-minting.md) | [androidJvm]<br>fun [estimateGasForMinting](estimate-gas-for-minting.md)(): [GasEstimation](../../com.kycdao.android.sdk.model/-gas-estimation/index.md)<br>Creates an estimation for the gas fees during minting |
| [getNFTImages](get-n-f-t-images.md) | [androidJvm]<br>fun [getNFTImages](get-n-f-t-images.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[TokenImage](../../com.kycdao.android.sdk.model/-token-image/index.md)&gt;<br>Provides the user selectable NFT images |
| [login](login.md) | [androidJvm]<br>suspend fun [login](login.md)()<br>Logs in the user to the current session The user will be redirected to their wallet, where they have to sign a session data in order to login |
| [mint](mint.md) | [androidJvm]<br>suspend fun [mint](mint.md)(): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?<br>Mints the previously chosen NFT image |
| [requestMinting](request-minting.md) | [androidJvm]<br>suspend fun [requestMinting](request-minting.md)(selectedImage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Authorizes the minting process for a selected NFT image Returns after the minting of the selected NFT has been authorized |
| [resumeOnEmailConfirmed](resume-on-email-confirmed.md) | [androidJvm]<br>suspend fun [resumeOnEmailConfirmed](resume-on-email-confirmed.md)()<br>Starts polling the backend and suspends until the email is confirmed. |
| [resumeOnVerificationCompleted](resume-on-verification-completed.md) | [androidJvm]<br>suspend fun [resumeOnVerificationCompleted](resume-on-verification-completed.md)()<br>Starts polling the backend and suspends until the identity verification result is available. |
| [sendConfirmationEmail](send-confirmation-email.md) | [androidJvm]<br>suspend fun [sendConfirmationEmail](send-confirmation-email.md)()<br>Sends a confirmation email to the [provided](set-personal-data.md) email address if the address in question has not been confirmed previously |
| [setPersonalData](set-personal-data.md) | [androidJvm]<br>suspend fun [setPersonalData](set-personal-data.md)(personalData: [PersonalData](../../com.kycdao.android.sdk.model/-personal-data/index.md))<br>Save the personal information of the user. If the provided email address is not yet confirmed then a confirmation email will be sent. |
| [startIdentification](start-identification.md) | [androidJvm]<br>suspend fun [startIdentification](start-identification.md)(activity: [ComponentActivity](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html)): [IdentityFlowResult](../../com.kycdao.android.sdk.model/-identity-flow-result/index.md)<br>Starts the identity verification process. |

## Properties

| Name | Summary |
|---|---|
| [chainId](chain-id.md) | [androidJvm]<br>val [chainId](chain-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [disclaimerAccepted](disclaimer-accepted.md) | [androidJvm]<br>val [disclaimerAccepted](disclaimer-accepted.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Represents whether the disclaimer has been accepted or not. |
| [emailConfirmed](email-confirmed.md) | [androidJvm]<br>val [emailConfirmed](email-confirmed.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Represents whether the email address associated with the session has been confirmed or not. |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [loggedIn](logged-in.md) | [androidJvm]<br>val [loggedIn](logged-in.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the user is logged in or not. |
| [requiredInformationProvided](required-information-provided.md) | [androidJvm]<br>val [requiredInformationProvided](required-information-provided.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A value representing whether all the necessary information has been provided or not. The necessary informations include the following: Both residency information and email address has been provided and we know if the user is a legal entity or not. |
| [verificationStatus](verification-status.md) | [androidJvm]<br>val [verificationStatus](verification-status.md): [VerificationStatus](../../com.kycdao.android.sdk.model/-verification-status/index.md)<br>The verification status of the user |
| [walletAddress](wallet-address.md) | [androidJvm]<br>val [walletAddress](wallet-address.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Wallet address used to create the session |
| [walletSession](wallet-session.md) | [androidJvm]<br>val [walletSession](wallet-session.md): [WalletSession](../../com.kycdao.android.sdk.wallet/-wallet-session/index.md)<br>The wallet session associated with this KYCSession, used to communicate with a wallet |
