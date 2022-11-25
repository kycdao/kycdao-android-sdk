package com.kycdao.android.sdk.model

import java.io.Serializable

data class TokenImage(
	val id: String,
	val imageType: ImageType,
	val url: String,
) : Serializable
