package com.akatsuki.safeguardpro.service.model

import com.google.gson.annotations.SerializedName

data class Epi(
    var id: Int = 0,
    @SerializedName("nome")
    val nomeEpi: String = "",
    val descricao: String = "",
    val ca: Int = 0,
    @SerializedName("validade")
    val validadeFabricacao: String = "",
    @SerializedName("tempo_uso")
    val validadeTempoUso: String = ""
)