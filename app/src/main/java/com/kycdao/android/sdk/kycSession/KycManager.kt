package com.kycdao.android.sdk.kycSession


import com.kycdao.android.sdk.KoinContainer.networkDatasource
import com.kycdao.android.sdk.dto.SmartContractConfigDto
import com.kycdao.android.sdk.dto.StatusDto
import com.kycdao.android.sdk.dto.toModel
import com.kycdao.android.sdk.exceptions.ConfigNotFoundException
import com.kycdao.android.sdk.exceptions.UnsupportedNetworkException
import com.kycdao.android.sdk.exceptions.Web3Exception
import com.kycdao.android.sdk.exceptions.toException
import com.kycdao.android.sdk.model.Network
import com.kycdao.android.sdk.model.NetworkOption
import com.kycdao.android.sdk.model.VerificationType
import com.kycdao.android.sdk.model.functions.token_validation.HasValidTokenFunction
import com.kycdao.android.sdk.network.request_models.CreateSessionRequestBody
import com.kycdao.android.sdk.wallet.WalletSession
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.http.HttpService

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
		} ?: throw UnsupportedNetworkException(walletSession.getChainId())
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
			throw ConfigNotFoundException(desiredNetwork.name,VerificationType.KYC)
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
	suspend fun hasValidToken(
		verificationType: VerificationType,
		walletAddress: String,
		walletSession: WalletSession
	) : Boolean{
		return hasValidToken(
			verificationType,
			walletAddress,
			networkOption = NetworkOption(
				walletSession.getChainId(),
				walletSession.rpcURL
			)
		)
	}
	suspend fun hasValidToken(
		verificationType: VerificationType,
		walletAddress: String,
		networkOption: NetworkOption
	): Boolean {
		val networks = fetchSupportedNetworks()
		val selectedNetworkMetaData = networks.find { network ->
			network.caip2id == networkOption.chainId
		} ?: throw UnsupportedNetworkException(networkOption.chainId)

		val status: StatusDto = networkDatasource.getStatus()

		val contractConfig = status.smart_contracts_info[selectedNetworkMetaData.id]
			?.get(verificationType)
			?: throw ConfigNotFoundException(selectedNetworkMetaData.name,verificationType)

		val clientURL = networkOption.rpcURL
			?: "https://polygon-mumbai.infura.io/v3/8edae24121f74398b57da7ff5a3729a4"
		val client = Web3j.build(HttpService(clientURL))
		val hasValidTokenFunction = HasValidTokenFunction(
			walletAddress
		)
		val transaction = Transaction.createFunctionCallTransaction(
			/* from = */ walletAddress,
			/* nonce = */ null,
			/* gasPrice = */ null,
			/* gasLimit = */ null,
			/* to = */ contractConfig.address,
			/* data = */ FunctionEncoder.encode(hasValidTokenFunction)
		)
		val ethCallResponse =
			client.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get()
		if(ethCallResponse.error != null){
			throw ethCallResponse.error.toException()
		}
		val result = FunctionReturnDecoder.decode(
			ethCallResponse.value,
			hasValidTokenFunction.outputParameters
		)
		return result[0].value as Boolean
	}
}