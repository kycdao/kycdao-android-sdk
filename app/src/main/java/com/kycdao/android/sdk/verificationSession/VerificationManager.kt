package com.kycdao.android.sdk.verificationSession


import com.kycdao.android.sdk.CustomKoinComponent
import com.kycdao.android.sdk.dto.SmartContractConfigDto
import com.kycdao.android.sdk.dto.StatusDto
import com.kycdao.android.sdk.dto.toModel
import com.kycdao.android.sdk.exceptions.ConfigNotFoundException
import com.kycdao.android.sdk.exceptions.UnsupportedNetworkException
import com.kycdao.android.sdk.exceptions.toException
import com.kycdao.android.sdk.model.Network
import com.kycdao.android.sdk.model.NetworkOption
import com.kycdao.android.sdk.model.VerificationType
import com.kycdao.android.sdk.model.functions.token_validation.HasValidTokenFunction
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.NetworkDatasourceImpl
import com.kycdao.android.sdk.network.request_models.CreateSessionRequestBody
import com.kycdao.android.sdk.wallet.WalletSession
import com.kycdao.android.sdk.wallet.defaultNetworkConfigs
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.http.HttpService

/**
 * A class used for verification related tasks, like querying verification status for different wallets or creating verification sessions.
 */
object VerificationManager{

	data class Configuration(
		val apiKey: String,
		val environment: KycDaoEnvironment,
		val networkConfigurations: List<NetworkOption> = emptyList()
	)

	private var configuration: Configuration? = null


	private val environment get() = configuration?.environment
	private lateinit var networkDatasource: NetworkDatasource
	/**
	 * Creates a VerificationSession which is used to navigate through the verification flow
	 *
	 * @param walletAddress The wallet address which will be linked to the verificationSession
	 * @param walletSession An implementation of the WalletSession interface used to access the wallet of the user
	 *
	 * @return The created verificationSession
	 */
	suspend fun createSession(
		walletAddress: String,
		walletSession: WalletSession
	): VerificationSession {
		if(configuration==null) throw Exception("Configuration is missing")
		val networks = fetchSupportedNetworks()
		val chainID = walletSession.getChainId()
		val desiredNetwork = networks.find { network ->
			network.caip2id == chainID
		} ?: throw UnsupportedNetworkException(walletSession.getChainId())
		val createSessionRequestBody = CreateSessionRequestBody(
			address = walletAddress,
			blockchain = desiredNetwork.blockchain
		)
		val selectedRpcURL = getDesiredRPCUrl(desiredNetwork)

		val sessionData =
			networkDatasource.createSession(createSessionRequestBody).mapToSessionData()
		val status = networkDatasource.getStatus()
		var kycContractConfig: SmartContractConfigDto? = null
		status.smart_contracts_info[desiredNetwork.id]?.get(VerificationType.KYC)?.let { contract ->
			kycContractConfig = contract
		} ?: run {
			throw ConfigNotFoundException(desiredNetwork.name, VerificationType.KYC)
		}
		var accreditedContractConfig: SmartContractConfigDto? = null
		status.smart_contracts_info[desiredNetwork.id]?.get(VerificationType.AccreditedInvestor)
			?.let { contract ->
				accreditedContractConfig = contract
			}
		return VerificationSession(
			walletAddress = walletAddress,
			network = desiredNetwork,
			kycConfig = kycContractConfig?.toModel(),
			accreditedConfig = accreditedContractConfig?.toModel(),
			sessionData = sessionData,
			personaData = status.persona.toModel(),
			walletSession = walletSession,
			rpcURL = selectedRpcURL
		)
	}

	private suspend fun fetchSupportedNetworks(): List<Network> {
		return networkDatasource.getSupportedNetworks()
	}

	/**
	 * A function used to configure the behaviour of the SDK
	 * Has to be called exactly once.
	 *
	 * @param configuration A configuration class which contains values such as the environment and custom rpc endpoints.
	 *
	 */
	fun configure(
		configuration: Configuration
	) {
		if (this.configuration != null) throw Exception("Re-Configuration not allowed")
		this.configuration = configuration
		networkDatasource = CustomKoinComponent().get() {
			parametersOf(configuration.environment.serverURL)
		}

	}

	/**
	 * Checks on-chain whether the wallet has a valid token for the give verification type
	 *
	 * @param verificationType The type of verification we want to check.
	 * @param walletAddress The address of the wallet in question.
	 * @param walletSession The wallet connected currently to the session.
	 *
	 * @return the result of the check
	 */
	suspend fun hasValidToken(
		verificationType: VerificationType,
		walletAddress: String,
		walletSession: WalletSession
	): Boolean {
		return hasValidToken(
			verificationType,
			walletAddress,
			walletSession.getChainId(),
		)
	}
	private fun getDesiredRPCUrl(network: Network) : String{
		return configuration?.networkConfigurations?.find { it.chainId == network.caip2id }?.rpcURL
			?: network.rpcUrL
			?: defaultNetworkConfigs.find { it.chainId == network.caip2id }?.rpcURL
			?: throw UnsupportedNetworkException(network.caip2id)
	}

	/**
	 * Checks on-chain whether the wallet has a valid token for the give verification type
	 *
	 * @param verificationType The type of verification we want to check.
	 * @param walletAddress The address of the wallet in question.
	 * @param chainID The chainID of the network on which we want to check in <a href="https://github.com/ChainAgnostic/CAIPs/blob/master/CAIPs/caip-2.md">CAIP-2 format</a>
	 *
	 * @return the result of the check
	 */
	suspend fun hasValidToken(
		verificationType: VerificationType,
		walletAddress: String,
		chainID: String
	): Boolean {
		val networks = fetchSupportedNetworks()
		val selectedNetworkMetaData = networks.find { network ->
			network.caip2id == chainID
		} ?: throw UnsupportedNetworkException(chainID)

		val status: StatusDto = networkDatasource.getStatus()

		val contractConfig = status.smart_contracts_info[selectedNetworkMetaData.id]
			?.get(verificationType)
			?: throw ConfigNotFoundException(selectedNetworkMetaData.name, verificationType)

		val selectedRpcURL = getDesiredRPCUrl(selectedNetworkMetaData)
		val client = Web3j.build(HttpService(selectedRpcURL))
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
		if (ethCallResponse.error != null) {
			throw ethCallResponse.error.toException()
		}
		val result = FunctionReturnDecoder.decode(
			ethCallResponse.value,
			hasValidTokenFunction.outputParameters
		)
		return result[0].value as Boolean
	}
}