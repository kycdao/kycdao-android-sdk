//[app](../../../index.md)/[com.kycdao.android.sdk.dto](../index.md)/[UserDto](index.md)

# UserDto

[androidJvm]\
data class [UserDto](index.md)(val user_hash: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val ext_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val legal_entity: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, val residency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val disclaimer_accepted: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val email_confirmed: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val verification_requests: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[VerificationRequestDto](../-verification-request-dto/index.md)&gt; = emptyList(), val available_images: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [AvailableImageDto](../-available-image-dto/index.md)&gt; = emptyMap(), val blockchain_accounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[BlockchainAccountDto](../-blockchain-account-dto/index.md)&gt;)

## Constructors

| | |
|---|---|
| [UserDto](-user-dto.md) | [androidJvm]<br>fun [UserDto](-user-dto.md)(user_hash: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ext_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), legal_entity: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, residency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, disclaimer_accepted: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, email_confirmed: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, verification_requests: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[VerificationRequestDto](../-verification-request-dto/index.md)&gt; = emptyList(), available_images: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [AvailableImageDto](../-available-image-dto/index.md)&gt; = emptyMap(), blockchain_accounts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[BlockchainAccountDto](../-blockchain-account-dto/index.md)&gt;) |

## Functions

| Name | Summary |
|---|---|
| [mapToKycUser](map-to-kyc-user.md) | [androidJvm]<br>fun [mapToKycUser](map-to-kyc-user.md)(): [KycUser](../../com.kycdao.android.sdk.kycSession/-kyc-user/index.md) |

## Properties

| Name | Summary |
|---|---|
| [available_images](available_images.md) | [androidJvm]<br>val [available_images](available_images.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [AvailableImageDto](../-available-image-dto/index.md)&gt; |
| [blockchain_accounts](blockchain_accounts.md) | [androidJvm]<br>val [blockchain_accounts](blockchain_accounts.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[BlockchainAccountDto](../-blockchain-account-dto/index.md)&gt; |
| [disclaimer_accepted](disclaimer_accepted.md) | [androidJvm]<br>val [disclaimer_accepted](disclaimer_accepted.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [email](email.md) | [androidJvm]<br>val [email](email.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [email_confirmed](email_confirmed.md) | [androidJvm]<br>val [email_confirmed](email_confirmed.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [ext_id](ext_id.md) | [androidJvm]<br>val [ext_id](ext_id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [legal_entity](legal_entity.md) | [androidJvm]<br>val [legal_entity](legal_entity.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null |
| [residency](residency.md) | [androidJvm]<br>val [residency](residency.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [user_hash](user_hash.md) | [androidJvm]<br>val [user_hash](user_hash.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [verification_requests](verification_requests.md) | [androidJvm]<br>val [verification_requests](verification_requests.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[VerificationRequestDto](../-verification-request-dto/index.md)&gt; |
