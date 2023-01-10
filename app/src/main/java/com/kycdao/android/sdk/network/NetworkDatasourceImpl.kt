package com.kycdao.android.sdk.network

import com.kycdao.android.sdk.dto.*
import com.kycdao.android.sdk.model.Network
import com.kycdao.android.sdk.network.api.*
import com.kycdao.android.sdk.network.request_models.*
import com.kycdao.android.sdk.util.handleResponse

class NetworkDatasourceImpl(
    val api: APIService
) : NetworkDatasource {

    override suspend fun createSession(body: CreateSessionRequestBody) : SessionDto {
        return api.createSession(body).handleResponse()
    }

    override suspend fun login(body: LoginRequestBody) : UserDto{
        return api.login(body).handleResponse()
    }

    override suspend fun updateUser(body: UpdateUserRequestBody) : UserDto {
        return api.updateUser(body).handleResponse()
    }

    override suspend fun saveDisclaimer() {
        return api.saveDisclaimer().handleResponse()
    }

    override suspend fun sendEmailConfirm() {
        return api.sendEmailConfirm().handleResponse()
    }

    override suspend fun getSession(): SessionDto {
        return api.getSession().handleResponse()
    }

    override suspend fun getUser(): UserDto {
        return api.getUser().handleResponse()
    }

    override suspend fun getStatus(): StatusDto {
        return api.getStatus().handleResponse()
    }

    override suspend fun authorizeMinting(body: AuthorizeMintingRequestBody) : AuthorizeMintingResponse {
        return api.authorizeMinting(body).handleResponse()
    }

    override suspend fun sendMintToken(body: MintTokenBody) : TokenDetailsDto {
        return api.sendMintToken(body).handleResponse()
    }

    override suspend fun getSupportedNetworks(): List<Network> {
        return api.getNetworks().handleResponse()
    }

    override suspend fun getNewIdenticons() {
        return api.getNewIdenticons().handleResponse()
    }

}
