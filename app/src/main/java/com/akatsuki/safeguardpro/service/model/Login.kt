package com.akatsuki.safeguardpro.service.model

object Login {
    var userId = 0
    var userCpf = ""
    var userAdmin = false

    fun userConected(id: Int, cpf: String, admin: Boolean) {
        userId = id
        userCpf = cpf
        userAdmin = admin
    }
}