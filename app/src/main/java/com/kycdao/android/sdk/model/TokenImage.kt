package com.kycdao.android.sdk.model

import java.io.Serializable

data class TokenImage(
    val id: String,
    val extId: String,
    val imageType: ImageType,
    private val url: String
) : Serializable {

    fun getUrl(): String {
        return "$url?user_id=$extId"
    }
}
