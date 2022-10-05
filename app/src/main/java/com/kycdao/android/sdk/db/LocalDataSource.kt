package com.kycdao.android.sdk.db

import com.kycdao.android.sdk.dto.StatusDto
import com.kycdao.android.sdk.model.KycSession
import com.kycdao.android.sdk.model.KycUser
import com.kycdao.android.sdk.wcsession._WCSession

interface LocalDataSource {

    fun getKycSession() : KycSession
    fun saveKycSession(kycSession: KycSession)
    fun saveKycUser(kycUser: KycUser)

    fun getWCSession() : _WCSession
    fun saveWCSession(wcSession: _WCSession)

    fun getStatus() : StatusDto
    fun saveStatus(statusDto: StatusDto)
}