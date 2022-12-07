package com.kycdao.android.sdk.verificationSession

import com.kycdao.android.sdk.model.*
import java.io.Serializable

data class User(
	val id: Long? = null,
	val extId: String? = null,
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
			it.verificationType == VerificationType.KYC && it.status == VerificationStatus.VERIFIED
		}
	}

	fun verificationStatus(): VerificationStatus {
		val statuses = verificationRequests.map {
			if (it.verificationType == VerificationType.KYC) {
				VerificationStatus.NOT_VERIFIED
			}
			it.status
		}
		return when {
			statuses.isEmpty() -> {
				VerificationStatus.NOT_VERIFIED
			}
			statuses.contains(VerificationStatus.VERIFIED) -> {
				VerificationStatus.VERIFIED
			}
			statuses.contains(VerificationStatus.PROCESSING) -> {
				VerificationStatus.PROCESSING
			}
			else -> {
				VerificationStatus.NOT_VERIFIED
			}
		}
	}

	fun isEmailConfirmed(): Boolean {
		return emailConfirmed?.isNotEmpty() ?: false
	}
}