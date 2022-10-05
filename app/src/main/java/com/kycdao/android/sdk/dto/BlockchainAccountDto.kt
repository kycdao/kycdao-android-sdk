package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.BlockchainAccount

data class BlockchainAccountDto(
    val id: Long,
    val address: String
) {
    fun mapToBlockchainAccount() : BlockchainAccount {
        return BlockchainAccount(
            id = id,
            address = address
        )
    }
}
