package com.kycdao.android.sdk.kycSession

import com.kycdao.android.sdk.model.*
import java.io.Serializable

data class KycUser(
	val id: Long? = null,
	val extId: String = "",
	var email: String? = null,
	var residency: String? = null,
	var isLegalEntity: Boolean? = null,
	var emailConfirmed: String? = null,
	val disclaimerAccepted: String? = null,
	val verificationRequests: List<VerificationRequest> = emptyList(),
	val availableImages: List<TokenImage> = emptyList(),
	val blockchainAccounts: List<BlockchainAccount> = emptyList()
) : Serializable {
	fun isIdentityVerified(): Boolean {
		return verificationRequests.any {
			it.verificationType == VerificationType.KYC && it.status == VerificationStatus.Verified
		}
	}

	fun isEmailConfirmed(): Boolean {
		return emailConfirmed?.isNotEmpty() ?: false
	}
}