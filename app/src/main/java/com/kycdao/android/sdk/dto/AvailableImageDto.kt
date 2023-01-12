package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.ImageType
import com.kycdao.android.sdk.model.TokenImage

data class AvailableImageDto(
    val image_type: ImageType,
    val url: String,
) {
    fun mapToAvailableImage(id: String, extId: String): TokenImage {
        return TokenImage(
            id,
			extId,
            imageType = image_type,
            url = url
        )
    }
}
