//[app](../../../index.md)/[com.kycdao.android.sdk.model](../index.md)/[KycUser](index.md)

# KycUser

[androidJvm]\
data class [KycUser](index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, val extId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, var email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var residency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var isLegalEntity: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, var emailConfirmed: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val disclaimerAccepted: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val verificationRequests: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[VerificationRequest](../-verification-request/index.md)&gt; = emptyList(), val availableImages: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../-available-image/index.md)&gt; = emptyList(), val blockchainAccounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[BlockchainAccount](../-blockchain-account/index.md)&gt; = emptyList()) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html)

## Constructors

| | |
|---|---|
| [KycUser](-kyc-user.md) | [androidJvm]<br>fun [KycUser](-kyc-user.md)(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, extId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, residency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, isLegalEntity: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, emailConfirmed: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, disclaimerAccepted: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, verificationRequests: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[VerificationRequest](../-verification-request/index.md)&gt; = emptyList(), availableImages: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../-available-image/index.md)&gt; = emptyList(), blockchainAccounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[BlockchainAccount](../-blockchain-account/index.md)&gt; = emptyList()) |

## Functions

| Name | Summary |
|---|---|
| [isEmailConfirmed](is-email-confirmed.md) | [androidJvm]<br>fun [isEmailConfirmed](is-email-confirmed.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isIdentityVerified](is-identity-verified.md) | [androidJvm]<br>fun [isIdentityVerified](is-identity-verified.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Properties

| Name | Summary |
|---|---|
| [availableImages](available-images.md) | [androidJvm]<br>val [availableImages](available-images.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AvailableImage](../-available-image/index.md)&gt; |
| [blockchainAccounts](blockchain-accounts.md) | [androidJvm]<br>val [blockchainAccounts](blockchain-accounts.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[BlockchainAccount](../-blockchain-account/index.md)&gt; |
| [disclaimerAccepted](disclaimer-accepted.md) | [androidJvm]<br>val [disclaimerAccepted](disclaimer-accepted.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [email](email.md) | [androidJvm]<br>var [email](email.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [emailConfirmed](email-confirmed.md) | [androidJvm]<br>var [emailConfirmed](email-confirmed.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [extId](ext-id.md) | [androidJvm]<br>val [extId](ext-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null |
| [isLegalEntity](is-legal-entity.md) | [androidJvm]<br>var [isLegalEntity](is-legal-entity.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null |
| [residency](residency.md) | [androidJvm]<br>var [residency](residency.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [verificationRequests](verification-requests.md) | [androidJvm]<br>val [verificationRequests](verification-requests.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[VerificationRequest](../-verification-request/index.md)&gt; |
