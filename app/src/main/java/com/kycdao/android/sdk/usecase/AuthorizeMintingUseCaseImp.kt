package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.model.KycSession
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.api.AuthorizeMintingRequestBody
import timber.log.Timber

class AuthorizeMintingUseCaseImp(
    val localDataSource: LocalDataSource,
    val networkDatasource: NetworkDatasource
) : AuthorizeMintingUseCase {

    override suspend fun invoke(selectedNftId: String, kycSession: KycSession) {
        val blockchainAccount = kycSession.sessionData.user.blockchainAccounts.first()

        Timber.d( "---------- Input ----------")
        Timber.d( "selectedNftId: $selectedNftId")
        Timber.d( "blockchainAccount: $blockchainAccount")

        val authorizeMintingResponse: AuthorizeMintingResponse = networkDatasource.authorizeMinting(
            AuthorizeMintingRequestBody(
                //network is currently baked in
                blockchain_account_id = blockchainAccount.id,
                selected_image_id = selectedNftId
            )
        )

        Timber.d( "---------- Output ----------")
        Timber.d( "authorizeMintingResponse: $authorizeMintingResponse")

        kycSession.authorizeMintingResponse = authorizeMintingResponse
    }
}