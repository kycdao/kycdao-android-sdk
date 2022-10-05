package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.network.NetworkDatasource
import timber.log.Timber

class SendEmailConfirmUseCaseImp(
    private val networkDatasource: NetworkDatasource
) : SendEmailConfirmUseCase {

    override suspend fun invoke() {
        Timber.d("sending confirmation email")
        networkDatasource.sendEmailConfirm()
    }
}