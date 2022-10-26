//[app](../../../index.md)/[com.kycdao.android.sdk.db](../index.md)/[LocalDataSource](index.md)

# LocalDataSource

[androidJvm]\
interface [LocalDataSource](index.md)

## Functions

| Name | Summary |
|---|---|
| [getKycSession](get-kyc-session.md) | [androidJvm]<br>abstract fun [getKycSession](get-kyc-session.md)(): [KycSession](../../com.kycdao.android.sdk.model/-kyc-session/index.md) |
| [getStatus](get-status.md) | [androidJvm]<br>abstract fun [getStatus](get-status.md)(): [StatusDto](../../com.kycdao.android.sdk.dto/-status-dto/index.md) |
| [getWCSession](get-w-c-session.md) | [androidJvm]<br>abstract fun [getWCSession](get-w-c-session.md)(): [_WCSession](../../com.kycdao.android.sdk.wcsession/_-w-c-session/index.md) |
| [saveKycSession](save-kyc-session.md) | [androidJvm]<br>abstract fun [saveKycSession](save-kyc-session.md)(kycSession: [KycSession](../../com.kycdao.android.sdk.model/-kyc-session/index.md)) |
| [saveKycUser](save-kyc-user.md) | [androidJvm]<br>abstract fun [saveKycUser](save-kyc-user.md)(kycUser: [KycUser](../../com.kycdao.android.sdk.model/-kyc-user/index.md)) |
| [saveStatus](save-status.md) | [androidJvm]<br>abstract fun [saveStatus](save-status.md)(statusDto: [StatusDto](../../com.kycdao.android.sdk.dto/-status-dto/index.md)) |
| [saveWCSession](save-w-c-session.md) | [androidJvm]<br>abstract fun [saveWCSession](save-w-c-session.md)(wcSession: [_WCSession](../../com.kycdao.android.sdk.wcsession/_-w-c-session/index.md)) |

## Inheritors

| Name |
|---|
| [LocalDataSourceImp](../-local-data-source-imp/index.md) |
