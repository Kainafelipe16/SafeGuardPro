package com.akatsuki.safeguardpro.service.repository.remote

import com.akatsuki.safeguardpro.service.model.Emprestimo
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EmprestimoService {
    @GET("listarEmprestimo")
    suspend fun getEmprestimos(): List<Emprestimo>

    @Multipart
    @POST("addEmprestimo")
    suspend fun createEmprestimo(
        @Part("dataEmprestimo") dataEmprestimo: RequestBody,
        @Part("funcionario_fk") funcionario_fk: RequestBody,
        @Part("epi_fk") epi_fk: RequestBody,
    ): Response<Emprestimo>

    @GET("selectEmprestimo/{emprestimo_id}")
    suspend fun getEmprestimoById(@Path("emprestimo_id") id: Int): Response<List<Emprestimo>>

    @Multipart
    @PUT("updateEmprestimo/{emprestimo_id}")
    suspend fun updateEmprestimo(
        @Path("emprestimo_id") emprestimo_id: Int,
        @Part("dataEmprestimo") dataEmprestimo: RequestBody,
        @Part("funcionario_fk") funcionario_fk: RequestBody,
        @Part("epi_fk") epi_fk: RequestBody,
    ): Response<Emprestimo>

    @DELETE("deletarEmprestimo/{emprestimo_id}")
    suspend fun deleteEmprestimoById(@Path("emprestimo_id") id: Int): Response<Emprestimo>
}