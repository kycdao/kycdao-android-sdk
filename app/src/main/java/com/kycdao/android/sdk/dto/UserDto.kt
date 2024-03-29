package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.verificationSession.User
import java.text.SimpleDateFormat
import java.util.*

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
    val blockchain_accounts: List<BlockchainAccountDto>,
    val subscription_expiry: String?
) {
    fun mapToKycUser() : User {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        return User(
            id = id,
            extId = ext_id,
            email = email,
            residency = residency,
            isLegalEntity = legal_entity,
            emailConfirmed = email_confirmed,
            disclaimerAccepted = disclaimer_accepted,
            verificationRequests = verification_requests.map { it.mapToVerificationRequest() },
            availableImages = available_images.map { it.value.mapToAvailableImage(it.key, ext_id) },
            blockchainAccounts = blockchain_accounts.map { it.mapToBlockchainAccount() },
            subscriptionExpiryDate = if(subscription_expiry!= null) format.parse(subscription_expiry)else null
        )
    }
}