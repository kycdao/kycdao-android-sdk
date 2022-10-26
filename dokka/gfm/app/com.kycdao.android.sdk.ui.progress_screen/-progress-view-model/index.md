//[app](../../../index.md)/[com.kycdao.android.sdk.ui.progress_screen](../index.md)/[ProgressViewModel](index.md)

# ProgressViewModel

[androidJvm]\
class [ProgressViewModel](index.md)(activity: [ComponentActivity](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

## Constructors

| | |
|---|---|
| [ProgressViewModel](-progress-view-model.md) | [androidJvm]<br>fun [ProgressViewModel](-progress-view-model.md)(activity: [ComponentActivity](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html)) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [connectToWallet](connect-to-wallet.md) | [androidJvm]<br>fun [connectToWallet](connect-to-wallet.md)() |
| [createKycSession](create-kyc-session.md) | [androidJvm]<br>fun [createKycSession](create-kyc-session.md)() |
| [login](login.md) | [androidJvm]<br>fun [login](login.md)() |
| [mintNft](mint-nft.md) | [androidJvm]<br>fun [mintNft](mint-nft.md)() |
| [savePersonalInfo](save-personal-info.md) | [androidJvm]<br>fun [savePersonalInfo](save-personal-info.md)() |
| [selectNft](select-nft.md) | [androidJvm]<br>fun [selectNft](select-nft.md)() |
| [startPersona](start-persona.md) | [androidJvm]<br>fun [startPersona](start-persona.md)() |

## Properties

| Name | Summary |
|---|---|
| [kycManager](kyc-manager.md) | [androidJvm]<br>val [kycManager](kyc-manager.md): [KycManager](../../com.kycdao.android.sdk.kycSession/-kyc-manager/index.md) |
| [myKycSession](my-kyc-session.md) | [androidJvm]<br>var [myKycSession](my-kyc-session.md): MutableStateFlow&lt;[KycSession](../../com.kycdao.android.sdk.model/-kyc-session/index.md)?&gt; |
| [myWalletSession](my-wallet-session.md) | [androidJvm]<br>var [myWalletSession](my-wallet-session.md): [WalletSession](../../com.kycdao.android.sdk.wallet/-wallet-session/index.md)? = null |
| [selectedImage](selected-image.md) | [androidJvm]<br>var [selectedImage](selected-image.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
