//[app](../../../index.md)/[com.kycdao.android.sdk.kycSession](../index.md)/[KycManager](index.md)

# KycManager

[androidJvm]\
object [KycManager](index.md)

A class responsible for managing the kycSession

## Functions

| Name | Summary |
|---|---|
| [createSession](create-session.md) | [androidJvm]<br>suspend fun [createSession](create-session.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), walletSession: [WalletSession](../../com.kycdao.android.sdk.wallet/-wallet-session/index.md)): [KycSession](../-kyc-session/index.md)<br>Creates a kycSession that will be used by the KycManager |
| [hasValidToken](has-valid-token.md) | [androidJvm]<br>suspend fun [hasValidToken](has-valid-token.md)(verificationType: [VerificationType](../../com.kycdao.android.sdk.model/-verification-type/index.md), walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), networkOption: [NetworkOption](../../com.kycdao.android.sdk.model/-network-option/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
