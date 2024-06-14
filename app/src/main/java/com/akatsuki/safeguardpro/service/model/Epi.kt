package com.akatsuki.safeguardpro.service.model

data class Epi(
    var id: Int = 0,
    val nomeEpi: String = "",
    val descricao: String = "",
    val ca: Int = 0,
    val validadeFabricacao: String = "",
    val validadeTempoUso: String = ""
)