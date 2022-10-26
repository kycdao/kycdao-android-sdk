//[app](../../../index.md)/[com.kycdao.android.sdk.network](../index.md)/[NetworkDatasourceImpl](index.md)

# NetworkDatasourceImpl

[androidJvm]\
class [NetworkDatasourceImpl](index.md)(val api: [APIService](../../com.kycdao.android.sdk.network.api/-a-p-i-service/index.md)) : [NetworkDatasource](../-network-datasource/index.md)

## Constructors

| | |
|---|---|
| [NetworkDatasourceImpl](-network-datasource-impl.md) | [androidJvm]<br>fun [NetworkDatasourceImpl](-network-datasource-impl.md)(api: [APIService](../../com.kycdao.android.sdk.network.api/-a-p-i-service/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [authorizeMinting](authorize-minting.md) | [androidJvm]<br>open suspend override fun [authorizeMinting](authorize-minting.md)(body: [AuthorizeMintingRequestBody](../../com.kycdao.android.sdk.network.api/-authorize-minting-request-body/index.md)): [AuthorizeMintingResponse](../../com.kycdao.android.sdk.dto/-authorize-minting-response/index.md) |
| [createSession](create-session.md) | [androidJvm]<br>open suspend override fun [createSession](create-session.md)(body: [CreateSessionRequestBody](../../com.kycdao.android.sdk.network.api/-create-session-request-body/index.md)): [SessionDto](../../com.kycdao.android.sdk.dto/-session-dto/index.md) |
| [getStatus](get-status.md) | [androidJvm]<br>open suspend override fun [getStatus](get-status.md)(): [StatusDto](../../com.kycdao.android.sdk.dto/-status-dto/index.md) |
| [getSupportedNetworks](get-supported-networks.md) | [androidJvm]<br>open suspend override fun [getSupportedNetworks](get-supported-networks.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Network](../../com.kycdao.android.sdk.model/-network/index.md)&gt; |
| [getUser](get-user.md) | [androidJvm]<br>open suspend override fun [getUser](get-user.md)(): [UserDto](../../com.kycdao.android.sdk.dto/-user-dto/index.md) |
| [login](login.md) | [androidJvm]<br>open suspend override fun [login](login.md)(body: [LoginRequestBody](../../com.kycdao.android.sdk.network.api/-login-request-body/index.md)): [UserDto](../../com.kycdao.android.sdk.dto/-user-dto/index.md) |
| [saveDisclaimer](save-disclaimer.md) | [androidJvm]<br>open suspend override fun [saveDisclaimer](save-disclaimer.md)() |
| [sendEmailConfirm](send-email-confirm.md) | [androidJvm]<br>open suspend override fun [sendEmailConfirm](send-email-confirm.md)() |
| [sendMintToken](send-mint-token.md) | [androidJvm]<br>open suspend override fun [sendMintToken](send-mint-token.md)(body: [MintTokenBody](../../com.kycdao.android.sdk.network.api/-mint-token-body/index.md)) |
| [updateUser](update-user.md) | [androidJvm]<br>open suspend override fun [updateUser](update-user.md)(body: [UpdateUserRequestBody](../../com.kycdao.android.sdk.network.api/-update-user-request-body/index.md)): [UserDto](../../com.kycdao.android.sdk.dto/-user-dto/index.md) |

## Properties

| Name | Summary |
|---|---|
| [api](api.md) | [androidJvm]<br>val [api](api.md): [APIService](../../com.kycdao.android.sdk.network.api/-a-p-i-service/index.md) |
