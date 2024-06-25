package com.akatsuki.safeguardpro.service.repository.remote

import com.akatsuki.safeguardpro.service.model.Funcionario
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE

import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface FuncionarioService {

    @GET("selectFuncionarios")
    suspend fun getFuncionarios(): List<Funcionario>

    @GET("selectFuncionarioById/{funcionario_id}")
    suspend fun getFuncionarioId(@Path("funcionario_id") id: Int): Response<List<Funcionario>>

    @GET("selectFuncionarioByCpf/{cpf}")
    suspend fun getFuncionarioByCpf(@Path("cpf") cpf: String): Response<List<Funcionario>>

    @DELETE("deleteFuncionarioById/{funcionario_id}")
    suspend fun deleteFuncionarioById(@Path("funcionario_id") id: Int): Response<Funcionario>

    @Multipart
    @POST("addFuncionario")
    suspend fun createFuncionario(
        @Part("nome") nome: RequestBody,
        @Part("sobrenome") sobrenome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>

    @Multipart
    @PUT("updateFuncionarioById/{funcionario_id}")
    suspend fun updateFuncionario(
        @Path("funcionario_id") funcionarioId: Int,
        @Part("nome") nome: RequestBody,
        @Part("sobrenome") sobrenome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>
}