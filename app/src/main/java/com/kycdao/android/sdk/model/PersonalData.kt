package com.kycdao.android.sdk.model

import java.io.Serializable

data class PersonalData(
    val email: String,
    val residency: String,
    val isLegalEntity: Boolean,
) : Serializable