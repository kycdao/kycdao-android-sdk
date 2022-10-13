package com.kycdao.android.sdk.kycSession


import androidx.activity.ComponentActivity
import com.kycdao.android.sdk.CustomKoinComponent
import com.kycdao.android.sdk.KoinContainer
import com.kycdao.android.sdk.KoinContainer.networkDatasource
import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.dto.SmartContractConfigDto
import com.kycdao.android.sdk.dto.toModel
import com.kycdao.android.sdk.model.*
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.api.AuthorizeMintingRequestBody
import com.kycdao.android.sdk.network.api.CreateSessionRequestBody
import com.kycdao.android.sdk.network.api.LoginRequestBody
import com.kycdao.android.sdk.network.api.MintTokenBody
import com.kycdao.android.sdk.usecase.*
import com.kycdao.android.sdk.util.asHexString
import com.kycdao.android.sdk.util.convertBigInteger
import com.kycdao.android.sdk.util.seconds
import com.kycdao.android.sdk.wallet.WalletSession
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt
import timber.log.Timber
import java.math.BigInteger
import kotlin.Exception

/**
 * A class responsible for managing the kycSession
 */
object KycManager {
    /**
     * Creates a kycSession that will be used by the KycManager
     *
     * Must be called first to ensure that KycManager has access to a session
     *
     * @param walletAddress The wallet address which will be linked to the kycSession
     * @param walletSession An implementation of the WalletSession interface used to access the wallet of the user
     *
     * @return The created kycSession
     */
    suspend fun createSession(walletAddress: String, walletSession: WalletSession): KycSession {
        val networks = fetchSupportedNetworks()
        val desiredNetwork = networks.find { network ->
            network.caip2id == walletSession.getChainId()
        } ?: throw Exception("Unsupported network")
        val createSessionRequestBody = CreateSessionRequestBody(
            address = walletAddress,
            blockchain = desiredNetwork.blockchain
        )
        val sessionData =
            networkDatasource.createSession(createSessionRequestBody).mapToSessionData()
        val status = networkDatasource.getStatus()
        var kycContractConfig: SmartContractConfigDto? = null
        status.smart_contracts_info[desiredNetwork.id]?.get(VerificationType.KYC)?.let { contract ->
            kycContractConfig = contract
        } ?: run {
            throw Exception("No kyc config found")
        }
        var accreditedContractConfig: SmartContractConfigDto? = null
        status.smart_contracts_info[desiredNetwork.id]?.get(VerificationType.AccreditedInvestor)
            ?.let { contract ->
                accreditedContractConfig = contract
            }
        return KycSession(
            walletAddress = walletAddress,
            network = desiredNetwork,
            kycConfig = kycContractConfig?.toModel(),
            accreditedConfig = accreditedContractConfig?.toModel(),
            sessionData = sessionData,
            personaData = status.persona.toModel(),
            walletConnection = walletSession
        )
    }

    private suspend fun fetchSupportedNetworks(): List<Network> {
        return networkDatasource.getSupportedNetworks()
    }
}