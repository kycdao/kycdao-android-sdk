package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.Persona
import com.withpersona.sdk2.inquiry.Environment

class PersonaDto(
    val template_id: String,
    val sandbox: Boolean
)

fun PersonaDto.toModel() : Persona{
    return Persona(
        this.template_id,
        environment = if(sandbox) Environment.SANDBOX else Environment.PRODUCTION
    )
}
