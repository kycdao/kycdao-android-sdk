package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.AvailableImage

data class AvailableImageDto(
    val image_type: String,
    val url: String,
) {
    fun mapToAvailableImage(id: String) : AvailableImage {
        return AvailableImage(
            id,
            imageType = image_type,
            url = url
        )
    }
}
