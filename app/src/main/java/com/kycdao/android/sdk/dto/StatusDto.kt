package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.VerificationType

private typealias NetworkType = String

data class StatusDto(
    val persona: PersonaDto,
    val smart_contracts_info: Map<NetworkType,Map<VerificationType, SmartContractConfigDto>>
)
