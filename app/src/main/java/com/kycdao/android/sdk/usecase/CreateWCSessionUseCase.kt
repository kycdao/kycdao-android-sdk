package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.wcsession._WCSession

interface CreateWCSessionUseCase {
    operator fun invoke() : _WCSession
}