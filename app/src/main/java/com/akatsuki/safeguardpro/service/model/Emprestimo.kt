package com.akatsuki.safeguardpro.service.model

data class Emprestimo(
    var emprestimo_id: Int = 0,
    val dataEmprestimo: String = "",
    val funcionario_fk: Int = 0,
    val epi_fk: Int = 0,
)