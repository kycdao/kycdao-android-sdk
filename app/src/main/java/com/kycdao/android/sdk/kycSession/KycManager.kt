package com.kycdao.android.sdk.kycSession


import com.kycdao.android.sdk.KoinContainer.networkDatasource
import com.kycdao.android.sdk.dto.SmartContractConfigDto
import com.kycdao.android.sdk.dto.toModel
import com.kycdao.android.sdk.model.*
import com.kycdao.android.sdk.network.api.CreateSessionRequestBody
import com.kycdao.android.sdk.wallet.WalletSession
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
            walletSession = walletSession
        )
    }

    private suspend fun fetchSupportedNetworks(): List<Network> {
        return networkDatasource.getSupportedNetworks()
    }
}