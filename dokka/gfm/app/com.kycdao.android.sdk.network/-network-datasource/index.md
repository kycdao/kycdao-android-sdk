//[app](../../../index.md)/[com.kycdao.android.sdk.network](../index.md)/[NetworkDatasource](index.md)

# NetworkDatasource

[androidJvm]\
interface [NetworkDatasource](index.md)

## Functions

| Name | Summary |
|---|---|
| [authorizeMinting](authorize-minting.md) | [androidJvm]<br>abstract suspend fun [authorizeMinting](authorize-minting.md)(body: [AuthorizeMintingRequestBody](../../com.kycdao.android.sdk.network.api/-authorize-minting-request-body/index.md)): [AuthorizeMintingResponse](../../com.kycdao.android.sdk.dto/-authorize-minting-response/index.md) |
| [createSession](create-session.md) | [androidJvm]<br>abstract suspend fun [createSession](create-session.md)(body: [CreateSessionRequestBody](../../com.kycdao.android.sdk.network.api/-create-session-request-body/index.md)): [SessionDto](../../com.kycdao.android.sdk.dto/-session-dto/index.md) |
| [getStatus](get-status.md) | [androidJvm]<br>abstract suspend fun [getStatus](get-status.md)(): [StatusDto](../../com.kycdao.android.sdk.dto/-status-dto/index.md) |
| [getSupportedNetworks](get-supported-networks.md) | [androidJvm]<br>abstract suspend fun [getSupportedNetworks](get-supported-networks.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Network](../../com.kycdao.android.sdk.model/-network/index.md)&gt; |
| [getUser](get-user.md) | [androidJvm]<br>abstract suspend fun [getUser](get-user.md)(): [UserDto](../../com.kycdao.android.sdk.dto/-user-dto/index.md) |
| [login](login.md) | [androidJvm]<br>abstract suspend fun [login](login.md)(body: [LoginRequestBody](../../com.kycdao.android.sdk.network.api/-login-request-body/index.md)): [UserDto](../../com.kycdao.android.sdk.dto/-user-dto/index.md) |
| [saveDisclaimer](save-disclaimer.md) | [androidJvm]<br>abstract suspend fun [saveDisclaimer](save-disclaimer.md)() |
| [sendEmailConfirm](send-email-confirm.md) | [androidJvm]<br>abstract suspend fun [sendEmailConfirm](send-email-confirm.md)() |
| [sendMintToken](send-mint-token.md) | [androidJvm]<br>abstract suspend fun [sendMintToken](send-mint-token.md)(body: [MintTokenBody](../../com.kycdao.android.sdk.network.api/-mint-token-body/index.md)) |
| [updateUser](update-user.md) | [androidJvm]<br>abstract suspend fun [updateUser](update-user.md)(body: [UpdateUserRequestBody](../../com.kycdao.android.sdk.network.api/-update-user-request-body/index.md)): [UserDto](../../com.kycdao.android.sdk.dto/-user-dto/index.md) |

## Inheritors

| Name |
|---|
| [NetworkDatasourceImpl](../-network-datasource-impl/index.md) |
