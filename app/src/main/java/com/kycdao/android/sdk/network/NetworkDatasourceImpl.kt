package com.kycdao.android.sdk.network

import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.dto.SessionDto
import com.kycdao.android.sdk.dto.StatusDto
import com.kycdao.android.sdk.dto.UserDto
import com.kycdao.android.sdk.network.api.*

class NetworkDatasourceImpl(
    val api: APIService
) : NetworkDatasource {

    override suspend fun createSession(body: CreateSessionRequestBody) : SessionDto {
        return api.createSession(body)
    }

    override suspend fun login(body: LoginRequestBody) : UserDto{
        return api.login(body)
    }

    override suspend fun updateUser(body: UpdateUserRequestBody) : UserDto {
        return api.updateUser(body)
    }

    override suspend fun saveDisclaimer() {
        return api.saveDisclaimer()
    }

    override suspend fun sendEmailConfirm() {
        return api.sendEmailConfirm()
    }

    override suspend fun getUser(): UserDto {
        return api.getUser()
    }

    override suspend fun getStatus(): StatusDto {
        return api.getStatus()
    }

    override suspend fun authorizeMinting(body: AuthorizeMintingRequestBody) : AuthorizeMintingResponse {
        return api.authorizeMinting(body)
    }

    override suspend fun sendMintToken(body: MintTokenBody) {
        return api.sendMintToken(body)
    }

}
