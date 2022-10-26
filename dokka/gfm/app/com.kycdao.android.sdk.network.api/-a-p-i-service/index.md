//[app](../../../index.md)/[com.kycdao.android.sdk.network.api](../index.md)/[APIService](index.md)

# APIService

[androidJvm]\
interface [APIService](index.md)

## Functions

| Name | Summary |
|---|---|
| [authorizeMinting](authorize-minting.md) | [androidJvm]<br>@POST(value = &quot;authorize_minting&quot;)<br>abstract suspend fun [authorizeMinting](authorize-minting.md)(@Bodybody: [AuthorizeMintingRequestBody](../-authorize-minting-request-body/index.md)): [AuthorizeMintingResponse](../../com.kycdao.android.sdk.dto/-authorize-minting-response/index.md) |
| [createSession](create-session.md) | [androidJvm]<br>@POST(value = &quot;session&quot;)<br>abstract suspend fun [createSession](create-session.md)(@Bodybody: [CreateSessionRequestBody](../-create-session-request-body/index.md)): [SessionDto](../../com.kycdao.android.sdk.dto/-session-dto/index.md) |
| [getNetworks](get-networks.md) | [androidJvm]<br>@GET(value = &quot;networks&quot;)<br>abstract suspend fun [getNetworks](get-networks.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Network](../../com.kycdao.android.sdk.model/-network/index.md)&gt; |
| [getStatus](get-status.md) | [androidJvm]<br>@GET(value = &quot;status&quot;)<br>abstract suspend fun [getStatus](get-status.md)(): [StatusDto](../../com.kycdao.android.sdk.dto/-status-dto/index.md) |
| [getUser](get-user.md) | [androidJvm]<br>@GET(value = &quot;user&quot;)<br>abstract suspend fun [getUser](get-user.md)(): [UserDto](../../com.kycdao.android.sdk.dto/-user-dto/index.md) |
| [login](login.md) | [androidJvm]<br>@POST(value = &quot;user&quot;)<br>abstract suspend fun [login](login.md)(@Bodybody: [LoginRequestBody](../-login-request-body/index.md)): [UserDto](../../com.kycdao.android.sdk.dto/-user-dto/index.md) |
| [saveDisclaimer](save-disclaimer.md) | [androidJvm]<br>@POST(value = &quot;disclaimer&quot;)<br>abstract suspend fun [saveDisclaimer](save-disclaimer.md)() |
| [sendEmailConfirm](send-email-confirm.md) | [androidJvm]<br>@POST(value = &quot;user/email_confirmation&quot;)<br>abstract suspend fun [sendEmailConfirm](send-email-confirm.md)() |
| [sendMintToken](send-mint-token.md) | [androidJvm]<br>@POST(value = &quot;token&quot;)<br>abstract suspend fun [sendMintToken](send-mint-token.md)(@Bodybody: [MintTokenBody](../-mint-token-body/index.md)) |
| [updateUser](update-user.md) | [androidJvm]<br>@PUT(value = &quot;user&quot;)<br>abstract suspend fun [updateUser](update-user.md)(@Bodybody: [UpdateUserRequestBody](../-update-user-request-body/index.md)): [UserDto](../../com.kycdao.android.sdk.dto/-user-dto/index.md) |
