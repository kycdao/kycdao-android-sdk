package com.kycdao.android.sdk.db

import com.kycdao.android.sdk.dto.StatusDto
import com.kycdao.android.sdk.model.KycSession
import com.kycdao.android.sdk.model.KycUser
import com.kycdao.android.sdk.wcsession._WCSession

class LocalDataSourceImp : LocalDataSource {

    //private var kycSession = KycSession()
    lateinit var wcSession: _WCSession
    lateinit var statusDto: StatusDto

    override fun getKycSession(): KycSession {
TODO()
    }

    override fun saveKycSession(kycSession: KycSession) {
       TODO()
    }

    override fun saveKycUser(kycUser: KycUser) {
      //  kycSession = kycSession.copy(
      //      kycUser = kycUser
      //  )
    }

    override fun getWCSession(): _WCSession {
        return wcSession
    }

    override fun saveWCSession(wcSession: _WCSession) {
        this.wcSession = wcSession
    }

    override fun getStatus(): StatusDto {
        return statusDto
    }

    override fun saveStatus(statusDto: StatusDto) {
        this.statusDto = statusDto
    }
}