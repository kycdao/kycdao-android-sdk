package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.api.AuthorizeMintingRequestBody
import timber.log.Timber

class AuthorizeMintingUseCaseImp(
    val localDataSource: LocalDataSource,
    val networkDatasource: NetworkDatasource
) : AuthorizeMintingUseCase {

    override suspend fun invoke(selectedNftId: String) {
        val kycSession = localDataSource.getKycSession()
        val blockchainAccount = kycSession.kycUser.blockchainAccounts.first()

        Timber.d( "---------- Input ----------")
        Timber.d( "selectedNftId: $selectedNftId")
        Timber.d( "blockchainAccount: $blockchainAccount")

        val authorizeMintingResponse = networkDatasource.authorizeMinting(
            AuthorizeMintingRequestBody(
                blockchain_account_id = blockchainAccount.id,
                selected_image_id = selectedNftId
            )
        )

        Timber.d( "---------- Output ----------")
        Timber.d( "authorizeMintingResponse: $authorizeMintingResponse")

        localDataSource.saveKycSession(kycSession.copy(
            authorizeMintingResponse = authorizeMintingResponse
        ))
    }
}