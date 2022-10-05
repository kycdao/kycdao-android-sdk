package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.api.UpdateUserRequestBody
import timber.log.Timber

class UpdateUserUseCaseImp(
    private val networkDatasource : NetworkDatasource,
    private val localDataSource: LocalDataSource
) : UpdateUserUseCase {

    override suspend fun invoke() {
        val kycSession = localDataSource.getKycSession()

        val email = kycSession.kycUser.email
        val residency = kycSession.kycUser.residency
        val legalEntity = kycSession.kycUser.isLegalEntity

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
            localDataSource.saveKycUser(userDto.mapToKycUser())
        } else {
            Timber.e( "Missed user information")
        }
    }
}