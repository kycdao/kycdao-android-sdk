package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.model.KycSession
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.api.UpdateUserRequestBody
import timber.log.Timber

class UpdateUserUseCaseImp(
    private val networkDatasource : NetworkDatasource,
    private val localDataSource: LocalDataSource
) : UpdateUserUseCase {

    override suspend fun invoke(kycSession: KycSession) {

        val email = kycSession.sessionData.user.email
        val residency = kycSession.sessionData.user.residency
        val legalEntity = kycSession.sessionData.user.isLegalEntity

        Timber.d( "---------- Input ----------")
        Timber.d( "email: $email")
        Timber.d( "residency: $residency")
        Timber.d( "legalEntity: $legalEntity")

        if (kycSession.hasUserInfo()) {
            val userDto = networkDatasource.updateUser(
                UpdateUserRequestBody(
                    email = email!!,
                    residency = residency!!,
                    legal_entity = legalEntity!!,
                )
            )
            val userFromNetwork = userDto.mapToKycUser()
            kycSession.apply {
                sessionData.user = userFromNetwork
            }
        } else {
            Timber.e( "Missed user information")
        }
    }
}