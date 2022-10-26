//[app](../../../index.md)/[com.kycdao.android.sdk.kycSession](../index.md)/[KycManager](index.md)/[savePersonalInfo](save-personal-info.md)

# savePersonalInfo

[androidJvm]\
suspend fun [savePersonalInfo](save-personal-info.md)(personalDataResult: [PersonalDataResult](../../com.kycdao.android.sdk.model/-personal-data-result/index.md))

Save the personal information of the user and sends confirmation email if email is not yet confirmed.

By calling this function the disclaimer is also accepted. The function finishes when the email is confirmed

## Parameters

androidJvm

| | |
|---|---|
| personalDataResult | The personal information to be saved wrapped in a PersonalDataResult class |
