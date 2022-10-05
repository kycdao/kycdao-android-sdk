package com.kycdao.android.sdk.network

import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.dto.StatusDto
import com.kycdao.android.sdk.dto.UserDto
import com.kycdao.android.sdk.dto.SessionDto
import com.kycdao.android.sdk.network.api.*

interface NetworkDatasource {

    suspend fun createSession(body: CreateSessionRequestBody) : SessionDto
    suspend fun login(body: LoginRequestBody) : UserDto
    suspend fun updateUser(body: UpdateUserRequestBody) : UserDto
    suspend fun saveDisclaimer()
    suspend fun sendEmailConfirm()
    suspend fun getUser() : UserDto
    suspend fun getStatus() : StatusDto
    suspend fun authorizeMinting(body: AuthorizeMintingRequestBody) : AuthorizeMintingResponse
    suspend fun sendMintToken(body: MintTokenBody)

}
