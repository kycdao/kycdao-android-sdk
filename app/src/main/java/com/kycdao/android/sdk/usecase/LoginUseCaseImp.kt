package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.network.api.LoginRequestBody
import com.kycdao.android.sdk.network.NetworkDatasource
import timber.log.Timber

class LoginUseCaseImp(
    private val localDataSource: LocalDataSource,
    private val networkDatasource : NetworkDatasource,
) : LoginUseCase {

    override suspend fun invoke() {
        val kycSession = localDataSource.getKycSession()
        kycSession.signature?.let {
            Timber.d( "---------- Input ----------")
            Timber.e( "signature: $it")
            val userDto = networkDatasource.login(LoginRequestBody(it))
            Timber.d( "---------- Output ----------")
            Timber.e( "userDto: $userDto")
            localDataSource.saveKycUser(userDto.mapToKycUser())
        } ?: run {
            // TODO error
            Timber.e( "signature is missing")
        }
    }
}