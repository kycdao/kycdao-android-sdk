package com.kycdao.android.sdk.model

import com.kycdao.android.sdk.dto.AuthorizeMintingResponse

data class KycSession(
    val walletConnected: Boolean = false,
    val kycUser: KycUser = KycUser(),
    val nonce: String? = null,
    val signature: String? = null,
    val identityVerificationCompleted: Boolean = false,
    val authorizeMintingResponse: AuthorizeMintingResponse? = null,
    val authorizeMintingFinished: Boolean = false,
    val feeCalculationFinished: Boolean = false,
    val mintTransactionId: String? = null,
    val mintTokenId: String? = null,
    val mintTokenSent: Boolean = false,
) {
    fun getState() : State {
        return when {
            !walletConnected() -> State.CONNECT_WALLET
            !hasSession() -> State.SESSION_REQUIRED
            !isLogged() -> State.LOGIN_REQUIRED
            !hasUserInfo() -> State.USER_INFORMATION_REQUIRED
            !isEmailConfirmed() -> State.WAIT_EMAIL_CONFIRMED
            !identityVerificationCompleted() -> State.IDENTITY_VERIFICATION
            !isIdentityVerified() -> State.POLL_IDENTITY_VERIFICATION_RESULT
            !nftSelected() -> State.NFT_IMAGE_SELECTION
            !authorizeMintingFinished() -> State.WAITING_FOR_MINTING_AUTHORISATION
            !feeCalculationFinished() -> State.CALCULATE_FEE
            !mintStarted() -> State.MINTING
            !mintFinished() -> State.CHECK_MINTING
            !mintTokenSent() -> State.POST_MINT_TOKEN_ID
            else -> State.COMPLETED
        }
    }

    fun loginProof() : String {
        return "kycDAO-login-${nonce}"
    }

    private fun walletConnected() : Boolean {
        return walletConnected
    }

    private fun hasSession(): Boolean {
        return !nonce.isNullOrBlank()
    }

    private fun isLogged() : Boolean {
        return  kycUser.id != null
    }

    fun isEmailConfirmed() : Boolean {
        return kycUser.isEmailConfirmed()
    }

    fun isDisclaimerAccepted() : Boolean {
        return kycUser.disclaimerAccepted?.isNotEmpty() ?: false
    }

    fun hasUserInfo(): Boolean {
        return !kycUser.email.isNullOrBlank() && !kycUser.residency.isNullOrBlank() && kycUser.isLegalEntity != null
    }

    private fun identityVerificationCompleted() : Boolean {
        return identityVerificationCompleted or isIdentityVerified()
    }

    private fun isIdentityVerified() : Boolean {
        return kycUser.isIdentityVerified()
    }

    private fun nftSelected(): Boolean {
        return authorizeMintingResponse?.tx_hash != null
    }

    private fun authorizeMintingFinished(): Boolean {
        return authorizeMintingFinished
    }

    private fun feeCalculationFinished(): Boolean {
        return feeCalculationFinished
    }

    private fun mintStarted(): Boolean {
        return mintTransactionId?.isNotEmpty() ?: false
    }

    private fun mintFinished(): Boolean {
        return mintTokenId?.isNotEmpty() ?: false
    }

    private fun mintTokenSent(): Boolean {
        return mintTokenSent
    }

}
