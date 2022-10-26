//[app](../../index.md)/[com.kycdao.android.sdk.network.api](index.md)

# Package com.kycdao.android.sdk.network.api

## Types

| Name | Summary |
|---|---|
| [APIService](-a-p-i-service/index.md) | [androidJvm]<br>interface [APIService](-a-p-i-service/index.md) |
| [AuthorizeMintingRequestBody](-authorize-minting-request-body/index.md) | [androidJvm]<br>data class [AuthorizeMintingRequestBody](-authorize-minting-request-body/index.md)(val blockchain_account_id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val network: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;PolygonMumbai&quot;, val verification_type: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;KYC&quot;, val selected_image_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [CreateSessionRequestBody](-create-session-request-body/index.md) | [androidJvm]<br>data class [CreateSessionRequestBody](-create-session-request-body/index.md)(val address: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val blockchain: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Ethereum&quot;) |
| [LoginRequestBody](-login-request-body/index.md) | [androidJvm]<br>data class [LoginRequestBody](-login-request-body/index.md)(val signature: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [MintTokenBody](-mint-token-body/index.md) | [androidJvm]<br>data class [MintTokenBody](-mint-token-body/index.md)(val authorization_code: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val token_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val minting_tx_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [UpdateUserRequestBody](-update-user-request-body/index.md) | [androidJvm]<br>data class [UpdateUserRequestBody](-update-user-request-body/index.md)(val email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val residency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val legal_entity: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
