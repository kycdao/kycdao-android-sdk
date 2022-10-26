//[app](../../../index.md)/[com.kycdao.android.sdk.kycSession](../index.md)/[KycSession](index.md)/[mint](mint.md)

# mint

[androidJvm]\
suspend fun [mint](mint.md)(): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?

Mints the previously chosen NFT image

This method can only be called successfully after the user was [authorized](request-minting.md) for minting with a selected image

#### Return

An URL for an explorer where the minting transaction can be viewed

## Parameters

androidJvm

| | |
|---|---|
| performTransaction | Lambda function which contains the actual minting call, using the WalletSession interface |
