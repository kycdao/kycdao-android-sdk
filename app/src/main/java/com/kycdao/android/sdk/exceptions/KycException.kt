package com.kycdao.android.sdk.exceptions

import com.bitraptors.networking.api.models.NetworkErrorResponse
import com.kycdao.android.sdk.model.KYCErrorResponse
import com.kycdao.android.sdk.model.VerificationType
import org.web3j.protocol.core.Response

sealed class KycException(override val message: String?=null, override val cause: Throwable?=null) : Exception()

data class KycNetworkCallException(val networkException: NetworkErrorResponse<KYCErrorResponse>) : KycException()

data class UnsupportedNetworkException(val unsupportedChainId: String)
	: KycException("Network with the following chainID is not supported: $unsupportedChainId")

class ConfigNotFoundException(network: String,verificationType: VerificationType)
	: KycException("Config not found for the following verification type/network: ${verificationType.name}/$network")

class WalletSessionNotAvailableException
	: KycException("In order to successfully connect you need to start listening first.")

class WalletSessionNotListening : KycException("Please make sure to start listening for connection events, before attempting to connect to a wallet. This can be done by calling the startListening function. ")
class UserAlreadyLoggedIn
	: KycException("A user is already logged into this session")
data class Web3Exception(
	val errorMessage: String?,
	val errorCode: Int,
	val errorData: String?
) : KycException()

open class WalletConnectException(override val cause: Throwable?) : KycException(cause = cause)

data class PersonalSignException(val throwable: Throwable) : WalletConnectException(cause = throwable)
data class SendTransactionException(val throwable: Throwable) : WalletConnectException(cause = throwable)

data class VerificationSessionIllegalAction(val reason: IllegalAction) : KycException()

object SuspensionTimeOutException : KycException(message = "Suspension took too long")

class EmailIsAlreadyConfirmed : KycException()
class NoBlockChainAccountFound : KycException()

sealed class PersonaException : KycException(){
	class ExternalIdMissing : PersonaException()
	class PersonaVerificationFailed : PersonaException()
}
data class GenericKycException(
	override val message: String?,
	override val cause: Throwable?
) : KycException()

enum class IllegalAction{
	NotLoggedIn,
	DisclaimerNotAccepted,
	EmailIsNotConfirmed,
	AuthorizationMissing,
	PersonalInformationMissing,
	YearIsZero
}

fun Response.Error.toException() : Web3Exception{
	return Web3Exception(
		errorMessage = message,
		errorCode = code,
		errorData = data
	)
}