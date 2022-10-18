package com.kycdao.android.sdk.network.api

import com.kycdao.android.sdk.dto.AuthorizeMintingResponse
import com.kycdao.android.sdk.dto.SessionDto
import com.kycdao.android.sdk.dto.StatusDto
import com.kycdao.android.sdk.dto.UserDto
import com.kycdao.android.sdk.model.Network
import retrofit2.http.*

interface APIService {
    @POST("session")
    suspend fun createSession(
        @Body body: CreateSessionRequestBody,
    ) : SessionDto

    @POST("user")
    suspend fun login(
        @Body body: LoginRequestBody,
    ) : UserDto

    @POST("disclaimer")
    suspend fun saveDisclaimer()

    @PUT("user")
    suspend fun updateUser(
        @Body body: UpdateUserRequestBody,
    ) : UserDto

    @POST("user/email_confirmation")
    suspend fun sendEmailConfirm()

    @GET("user")
    suspend fun getUser() : UserDto

    @GET("status")
    suspend fun getStatus() : StatusDto

    @POST("authorize_minting")
    suspend fun authorizeMinting(
        @Body body: AuthorizeMintingRequestBody,
    ) : AuthorizeMintingResponse

    @POST("token")
    suspend fun sendMintToken(
        @Body body: MintTokenBody,
    )

    @GET("networks")
    suspend fun getNetworks() : List<Network>
}
