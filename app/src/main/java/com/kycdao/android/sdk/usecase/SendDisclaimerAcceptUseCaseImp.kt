package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.network.NetworkDatasource
import timber.log.Timber

class SendDisclaimerAcceptUseCaseImp(
    private val localDataSource: LocalDataSource,
    private val networkDatasource: NetworkDatasource,
) : SendDisclaimerAcceptUseCase {

    override suspend fun invoke() {
        if (!localDataSource.getKycSession().isDisclaimerAccepted()) {
            Timber.d("sending disclaimer acceptance")
            networkDatasource.saveDisclaimer()
        } else {
            Timber.d("disclaimer acceptance has already been sent before")
        }
    }
}