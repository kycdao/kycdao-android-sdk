package com.kycdao.android.sdk.network

import com.kycdao.android.sdk.dto.*
import com.kycdao.android.sdk.model.Network
import com.kycdao.android.sdk.network.api.*
import com.kycdao.android.sdk.network.request_models.*

interface NetworkDatasource {

    suspend fun createSession(body: CreateSessionRequestBody) : SessionDto
    suspend fun login(body: LoginRequestBody) : UserDto
    suspend fun updateUser(body: UpdateUserRequestBody) : UserDto
    suspend fun saveDisclaimer()
    suspend fun sendEmailConfirm()
    suspend fun getSession() : SessionDto
    suspend fun getUser() : UserDto
    suspend fun getStatus() : StatusDto
    suspend fun authorizeMinting(body: AuthorizeMintingRequestBody) : AuthorizeMintingResponse
    suspend fun sendMintToken(body: MintTokenBody) : TokenDetailsDto
    suspend fun getSupportedNetworks() : List<Network>

}
