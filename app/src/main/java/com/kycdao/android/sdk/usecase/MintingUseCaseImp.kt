package com.kycdao.android.sdk.usecase

import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.model.VerificationType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.walletconnect.Session
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.generated.Uint32
import timber.log.Timber
import java.math.BigInteger
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MintingUseCaseImp(
    private val localDataSource: LocalDataSource,
    private val walletIntent: WalletIntent,
    private val ethGasPriceUseCase: EthGasPriceUseCase,
    private val estimateGasUseCaseImp: EstimateGasUseCase,
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : MintingUseCase {

    override suspend fun invoke(): Unit = suspendCoroutine { continuation ->
        ioScope.launch {
            val wcSession = localDataSource.getWCSession()
            val contractAddress = localDataSource.getStatus().smart_contracts_info["PolygonMumbai"]!![VerificationType.KYC]
            val gasPrice = ethGasPriceUseCase()
            val finalGasPrice = gasPrice.max(CalculateFeeUseCaseImp.MIN_GAS_PRICE)
            val gas = estimateGasUseCaseImp()
            val authCode = localDataSource.getKycSession().authorizeMintingResponse?.code!!.toLong()
            val walletAddress = wcSession.session.approvedAccounts()!!.first()

            val gasPriceString = "0x"+finalGasPrice.toString(16)
            val gasLimit = "0x"+gas.toString(16)
            val function = Function(
                "mint",
                listOf(Uint32(BigInteger.valueOf(authCode))),
                listOf()
            )
            val data = FunctionEncoder.encode(function)

            Timber.d( "---------- Input ----------")
            Timber.d( "authCode: $authCode")
            Timber.d( "walletAddress: $walletAddress")
            Timber.d( "contractAddress: $contractAddress")
            Timber.d( "gasPrice: $gasPriceString")
            Timber.d( "gas: $gasLimit")
            Timber.d( "data: $data")

            Timber.d( "Send minting transaction to the wallet")
            wcSession.session.performMethodCall(
                Session.MethodCall.SendTransaction(
                    id = System.currentTimeMillis(),
                    from = walletAddress,
                    to = contractAddress!!.address,
                    nonce = null,
                    gasPrice = gasPriceString,
                    gasLimit = gasLimit,
                    value = null,
                    data = data,
                ),
            ) {
                Timber.d( "Minting result from the wallet)")
                Timber.d( "result:${it.result}")
                Timber.d( "error:${it.error}")
                val transactionId = it.result as? String
                transactionId?.let {
                    Timber.d( "---------- Output ----------")
                    Timber.d( "transactionId: $transactionId")

                    localDataSource.saveKycSession(
                        localDataSource.getKycSession().copy(
                            mintTransactionId = transactionId
                        )
                    )
                }
                continuation.resume(Unit)
            }
            Timber.d( "Open wallet for minting")
            walletIntent(wcSession.config.toWCUri())
        }
    }
}