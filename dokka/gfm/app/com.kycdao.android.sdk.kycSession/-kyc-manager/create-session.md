//[app](../../../index.md)/[com.kycdao.android.sdk.kycSession](../index.md)/[KycManager](index.md)/[createSession](create-session.md)

# createSession

[androidJvm]\
suspend fun [createSession](create-session.md)(walletAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), walletSession: [WalletSession](../../com.kycdao.android.sdk.wallet/-wallet-session/index.md)): [KycSession](../-kyc-session/index.md)

Creates a kycSession that will be used by the KycManager

Must be called first to ensure that KycManager has access to a session

#### Return

The created kycSession

## Parameters

androidJvm

| | |
|---|---|
| walletAddress | The wallet address which will be linked to the kycSession |
| walletSession | An implementation of the WalletSession interface used to access the wallet of the user |
