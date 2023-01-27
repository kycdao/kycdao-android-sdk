# ``KycDao``

Composable Compliance

## Overview

React Native SDK of [kycDAO](https://kycdao.xyz/)

With the kycDAO Android SDK you can 
- check whether a wallet address have been verified and has a valid token. 
- go through the verification process from identification till kycNFT minting.

The SDK can be used by
- Wallets
- DApps
- Web2 Applications

### Documentation

The documentation of the SDK is available [here](https://docs.kycdao.xyz/mobilesdk/android-sdk/).

### Installation

#### Gradle

```
dependencies {
  ...
  implementation 'com.github.kycdao.sdk:android:0.1.3'
}
```

#### Importing to source file

Add an import at the top of your source file

With namespace import

```kotlin
import com.kycdao.android.sdk.*
```

That's it. You can start coding.

### Configuration

Set up the environment and [Configure the SDK](https://docs.kycdao.xyz/mobilesdk/android-sdk/configuresdk/) for your needs

> Important: It is recommended that you bring your own nodes when using the SDK, you can check the [Configure SDK](https://docs.kycdao.xyz/mobilesdk/android-sdk/configuresdk/) article to see how to set your own RPC URLs for your supported networks.

### Example

An example React Native project for a DApp implementation is available in the [example](https://github.com/kycdao/kycdao-android-sdk/tree/main/kyctestapp) folder.

### Integration

Learn the [Wallet Integration](https://docs.kycdao.xyz/mobilesdk/android-sdk/walletintegration/) or [DApp Integration](https://docs.kycdao.xyz/mobilesdk/android-sdk/dappandweb2/) specific steps to use the SDK

Help your users getting verified by implementing the [Onboarding](https://docs.kycdao.xyz/mobilesdk/android-sdk/onboarding/) flow

Deep dive into the SDK by visiting the [API documentation](https://kycdao.github.io/kycdao-android-sdk/)

### Supported networks

Main | Test
--- | ---
Polygon | Polygon Mumbai
Celo | Celo Alfajores


### Other platforms

The SDK is also available on other mobile platforms (iOS, React Native) and Web. 
You can browse our available SDKs [here](https://docs.kycdao.xyz/)

You can learn about [smart contract gating here](https://docs.kycdao.xyz/smartcontracts/)
