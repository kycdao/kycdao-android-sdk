package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.kycSession.KycUser

data class UserDto(
    val user_hash: String,
    val ext_id: String,
    val id: Long,
    val legal_entity: Boolean? = null,
    val residency: String? = null,
    val disclaimer_accepted: String? = null,
    val email: String? = null,
    val email_confirmed: String? = null,
    val verification_requests: List<VerificationRequestDto> = emptyList(),
    val available_images: Map<String, AvailableImageDto> = emptyMap(),
    val blockchain_accounts: List<BlockchainAccountDto>
) {
    fun mapToKycUser() : KycUser {
        return KycUser(
            id = id,
            extId = ext_id,
            email = email,
            residency = residency,
            isLegalEntity = legal_entity,
            emailConfirmed = email_confirmed,
            disclaimerAccepted = disclaimer_accepted,
            verificationRequests = verification_requests.map { it.mapToVerificationRequest() },
            availableImages = available_images.map { it.value.mapToAvailableImage(it.key) },
            blockchainAccounts = blockchain_accounts.map { it.mapToBlockchainAccount() }
        )
    }
}