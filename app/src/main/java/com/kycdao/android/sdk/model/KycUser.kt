package com.kycdao.android.sdk.model

import java.io.Serializable

data class KycUser (
    val id: Long? = null,
    val extId: String = "",
    val email: String? = null,
    val residency: String? = null,
    val isLegalEntity: Boolean? = null,
    val emailConfirmed: String? = null,
    val disclaimerAccepted: String? = null,
    val verificationRequests: List<VerificationRequest> = emptyList(),
    val availableImages: List<AvailableImage> = emptyList(),
    val blockchainAccounts: List<BlockchainAccount> = emptyList()
) : Serializable {
    fun isIdentityVerified(): Boolean {
        return verificationRequests.any {
            it.verificationType == "KYC" && it.status == "Verified"
        }
    }

    fun isEmailConfirmed(): Boolean {
        return emailConfirmed?.isNotEmpty() ?: false
    }
}