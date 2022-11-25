package com.kycdao.android.sdk

import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.usecase.IdentityVerificationUseCase
import org.koin.core.component.inject
import org.web3j.protocol.Web3j

internal object KoinContainer : CustomKoinComponent() {
	val networkDatasource: NetworkDatasource by inject()
	val web3j: Web3j by inject()
	val identityVerificationUseCase: IdentityVerificationUseCase by inject()
}