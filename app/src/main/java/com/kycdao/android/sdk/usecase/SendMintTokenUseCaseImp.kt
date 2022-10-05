package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.api.MintTokenBody
import timber.log.Timber

class SendMintTokenUseCaseImp(
    private val localDataSource: LocalDataSource,
    private val networkDatasource: NetworkDatasource
) : SendMintTokenUseCase {

    override suspend fun invoke() {
        val kycSession = localDataSource.getKycSession()
        val authorizationCode = kycSession.authorizeMintingResponse?.code ?: ""
        val tokenId = kycSession.mintTokenId ?: ""
        val mintingTxId = kycSession.mintTransactionId ?: ""

        Timber.d( "---------- Input ----------")
        Timber.d( "authorizationCode: $authorizationCode")
        Timber.d( "tokenId: $tokenId")
        Timber.d( "mintingTxId: $mintingTxId")

        networkDatasource.sendMintToken(MintTokenBody(
            authorization_code = authorizationCode,
            token_id = tokenId,
            minting_tx_id = mintingTxId
        ))
        localDataSource.saveKycSession(kycSession.copy(
            mintTokenSent = true
        ))
    }
}