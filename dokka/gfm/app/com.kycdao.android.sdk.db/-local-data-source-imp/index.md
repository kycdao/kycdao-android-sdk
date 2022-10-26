//[app](../../../index.md)/[com.kycdao.android.sdk.db](../index.md)/[LocalDataSourceImp](index.md)

# LocalDataSourceImp

[androidJvm]\
class [LocalDataSourceImp](index.md) : [LocalDataSource](../-local-data-source/index.md)

## Constructors

| | |
|---|---|
| [LocalDataSourceImp](-local-data-source-imp.md) | [androidJvm]<br>fun [LocalDataSourceImp](-local-data-source-imp.md)() |

## Functions

| Name | Summary |
|---|---|
| [getKycSession](get-kyc-session.md) | [androidJvm]<br>open override fun [getKycSession](get-kyc-session.md)(): [KycSession](../../com.kycdao.android.sdk.model/-kyc-session/index.md) |
| [getStatus](get-status.md) | [androidJvm]<br>open override fun [getStatus](get-status.md)(): [StatusDto](../../com.kycdao.android.sdk.dto/-status-dto/index.md) |
| [getWCSession](get-w-c-session.md) | [androidJvm]<br>open override fun [getWCSession](get-w-c-session.md)(): [_WCSession](../../com.kycdao.android.sdk.wcsession/_-w-c-session/index.md) |
| [saveKycSession](save-kyc-session.md) | [androidJvm]<br>open override fun [saveKycSession](save-kyc-session.md)(kycSession: [KycSession](../../com.kycdao.android.sdk.model/-kyc-session/index.md)) |
| [saveKycUser](save-kyc-user.md) | [androidJvm]<br>open override fun [saveKycUser](save-kyc-user.md)(kycUser: [KycUser](../../com.kycdao.android.sdk.model/-kyc-user/index.md)) |
| [saveStatus](save-status.md) | [androidJvm]<br>open override fun [saveStatus](save-status.md)(statusDto: [StatusDto](../../com.kycdao.android.sdk.dto/-status-dto/index.md)) |
| [saveWCSession](save-w-c-session.md) | [androidJvm]<br>open override fun [saveWCSession](save-w-c-session.md)(wcSession: [_WCSession](../../com.kycdao.android.sdk.wcsession/_-w-c-session/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [statusDto](status-dto.md) | [androidJvm]<br>lateinit var [statusDto](status-dto.md): [StatusDto](../../com.kycdao.android.sdk.dto/-status-dto/index.md) |
| [wcSession](wc-session.md) | [androidJvm]<br>lateinit var [wcSession](wc-session.md): [_WCSession](../../com.kycdao.android.sdk.wcsession/_-w-c-session/index.md) |
