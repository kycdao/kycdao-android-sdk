package com.kycdao.android.sdk.usecase

interface WalletIntent {
    operator fun invoke(wcUri: String)
}