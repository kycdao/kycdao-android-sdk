package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.dto.SessionDto
import com.kycdao.android.sdk.network.api.CreateSessionRequestBody
import com.kycdao.android.sdk.network.NetworkDatasource
import timber.log.Timber

class CreateKycSessionUseCaseImp(
    private val networkDatasource : NetworkDatasource,
    private val localDataSource: LocalDataSource
) : CreateKycSessionUseCase {

    override suspend fun invoke(walletAddress: String) {
        Timber.d( "create kyc session")
        Timber.d( "---------- Input ----------")
        Timber.d( "walletAddress: $walletAddress")
        val status = networkDatasource.getStatus()
        localDataSource.saveStatus(status)

        val sessionDto: SessionDto = networkDatasource.createSession(CreateSessionRequestBody(walletAddress))
        Timber.d( "---------- Output ----------")
        Timber.d( "sessionDto: $sessionDto")
        //localDataSource.saveKycSession(sessionDto.mapToKycSession())
    }
}