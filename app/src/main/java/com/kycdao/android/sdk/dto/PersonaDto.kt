package com.kycdao.android.sdk.dto

import com.kycdao.android.sdk.model.Persona

class PersonaDto(
    val template_id: String
)

fun PersonaDto.toModel() : Persona{
    return Persona(this.template_id)
}
