package com.akatsuki.safeguardpro.service.repository.remote

import com.akatsuki.safeguardpro.service.model.Funcionario
import okhttp3.RequestBody
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface FuncionarioService {

    @GET("listarFuncionario")
    suspend fun getFuncionarios(): List<Funcionario>

    @GET("selectFuncionario/{funcionario_id}")
    suspend fun getFuncionarioId(@Path("funcionario_id") id: Int): Response<List<Funcionario>>

    @GET("selectFuncionarioCPF/{cpf}")
    suspend fun getFuncionarioByCpf(@Path("cpf") cpf: String): Response<List<Funcionario>>

    @GET("deleteFuncionario/{funcionario_id}")
    suspend fun deleteFuncionarioById(@Path("funcionario_id") id: Int): Response<List<Funcionario>>

    @Multipart
    @POST("addFuncionario")
    suspend fun createFuncionario(
        @Part("nome") nome: RequestBody,
        @Part("sobrenome") sobrenome: RequestBody,
        @Part("cpf") cpf: RequestBody
    ): Response<Funcionario>

    @Multipart
    @PUT("updateFuncionario/{funcionario_id}")
    suspend fun updateFuncionario(
        @Path("funcionario_id") funcionarioId: Int,
        @Part("nome") nome: RequestBody,
        @Part("sobrenome") sobrenome: RequestBody,
        @Part("cpf") cpf: RequestBody
        ): Response<Funcionario>
}