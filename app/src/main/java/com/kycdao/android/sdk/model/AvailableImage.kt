package com.kycdao.android.sdk.model

import java.io.Serializable

data class AvailableImage(
    val id: String,
    val imageType: String,
    val url: String,
) : Serializable
