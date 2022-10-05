package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.model.VerificationType
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.generated.Uint32
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.request.Transaction
import timber.log.Timber
import java.math.BigInteger

class EstimateGasUseCaseImp(
    private val localDataSource: LocalDataSource,
    private val web3j: Web3j,
) : EstimateGasUseCase {

    override suspend fun invoke() : BigInteger {
        val walletAddress = localDataSource.getWCSession().session.approvedAccounts()!!.first()
        val contractAddress = localDataSource.getStatus().smart_contracts_info["PolygonMumbai"]!![VerificationType.KYC]
        val authCode = localDataSource.getKycSession().authorizeMintingResponse?.code!!.toLong()

        Timber.d( "---------- Input ----------")
        Timber.d( "walletAddress: $walletAddress")
        Timber.d( "contractAddress: $contractAddress")
        Timber.d( "authCode: $authCode")


        val function = Function(
            "mint",
            listOf(Uint32(BigInteger.valueOf(authCode))),
            listOf()
        )

        val transaction = Transaction.createFunctionCallTransaction(
            walletAddress,
            null,
            null,
            null,
            contractAddress!!.address,
            FunctionEncoder.encode(function)
        )
        val ethEstimateGas = web3j.ethEstimateGas(transaction).sendAsync().get()

        Timber.d( "---------- Output ----------")


        if (ethEstimateGas.error != null) {
            Timber.d( "error.code: ${ethEstimateGas.error?.code}")
            Timber.d( "error.data: ${ethEstimateGas.error?.data}")
            Timber.d( "error.message: ${ethEstimateGas.error?.message}")
            // TODO error
        }

        Timber.d( "amountUsed: ${ethEstimateGas.amountUsed}")

        return ethEstimateGas.amountUsed
    }

}