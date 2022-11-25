package com.kycdao.android.sdk.network.api

import com.bitraptors.networking.api.models.NetworkResponse
import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.dto.SessionDto
import com.kycdao.android.sdk.dto.StatusDto
import com.kycdao.android.sdk.dto.UserDto
import com.kycdao.android.sdk.model.KYCErrorResponse
import com.kycdao.android.sdk.model.Network
import com.kycdao.android.sdk.network.request_models.*
import retrofit2.http.*

typealias EmptyResponse = Unit

interface APIService {
    @POST("session")
    suspend fun createSession(
        @Body body: CreateSessionRequestBody,
    ) : NetworkResponse<SessionDto,KYCErrorResponse>

    @POST("user")
    suspend fun login(
        @Body body: LoginRequestBody,
    ) : NetworkResponse<UserDto,KYCErrorResponse>

    @POST("disclaimer")
    suspend fun saveDisclaimer() : NetworkResponse<EmptyResponse, KYCErrorResponse>

    @PUT("user")
    suspend fun updateUser(
        @Body body: UpdateUserRequestBody,
    ) : NetworkResponse<UserDto,KYCErrorResponse>

    @POST("user/email_confirmation")
    suspend fun sendEmailConfirm() : NetworkResponse<EmptyResponse, KYCErrorResponse>

    @GET("user")
    suspend fun getUser() : NetworkResponse<UserDto,KYCErrorResponse>

    @GET("status")
    suspend fun getStatus() :NetworkResponse<StatusDto,KYCErrorResponse>

    @POST("authorize_minting")
    suspend fun authorizeMinting(
        @Body body: AuthorizeMintingRequestBody,
    ) : NetworkResponse<AuthorizeMintingResponse,KYCErrorResponse>

    @POST("token")
    suspend fun sendMintToken(
        @Body body: MintTokenBody,
    ) : NetworkResponse<EmptyResponse,KYCErrorResponse>

    @GET("networks")
    suspend fun getNetworks() : NetworkResponse<List<Network>,KYCErrorResponse>
}
