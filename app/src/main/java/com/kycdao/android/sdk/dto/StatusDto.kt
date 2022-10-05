package com.kycdao.android.sdk.dto

data class StatusDto(
    val persona: PersonaDto,
    val smart_contracts: Map<String, SmartContactDto>
)
