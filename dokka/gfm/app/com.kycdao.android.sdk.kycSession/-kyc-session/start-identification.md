//[app](../../../index.md)/[com.kycdao.android.sdk.kycSession](../index.md)/[KycSession](index.md)/[startIdentification](start-identification.md)

# startIdentification

[androidJvm]\
suspend fun [startIdentification](start-identification.md)(activity: [ComponentActivity](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html)): [IdentityFlowResult](../../com.kycdao.android.sdk.model/-identity-flow-result/index.md)

Starts the identity verification process.

#### Return

The result of the identity verification flow. It only tells whether the user completed the identity flow or cancelled it. Information regarding the validity of the identity verification can be accessed at [verificationStatus](verification-status.md)

## See also

androidJvm

| | |
|---|---|
| [com.kycdao.android.sdk.kycSession.KycSession](resume-on-verification-completed.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| activity | The activity that starts the new activity containing the persona process |
