package com.kycdao.android.sdk

import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.usecase.IdentityVerificationUseCase
import com.kycdao.android.sdk.usecase.PollEmailConfirmedUseCase
import com.kycdao.android.sdk.usecase.PollIdentityVerificationRequestUseCase
import com.kycdao.android.sdk.usecase.UpdateUserUseCase
import org.koin.core.component.inject
import org.web3j.protocol.Web3j

internal object KoinContainer : CustomKoinComponent() {
    val networkDatasource: NetworkDatasource by inject()
    val web3j: Web3j by inject()
    val updateUserUseCase: UpdateUserUseCase by inject()
    val pollEmailUseCase: PollEmailConfirmedUseCase by inject()
    val pollIdentityVerificationRequestUseCase: PollIdentityVerificationRequestUseCase by inject()
    val identityVerificationUseCase: IdentityVerificationUseCase by inject()
}