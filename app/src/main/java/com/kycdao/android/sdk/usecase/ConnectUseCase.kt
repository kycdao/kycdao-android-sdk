package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.wcsession._WCSession

interface ConnectUseCase {
    suspend operator fun invoke() : _WCSession
}