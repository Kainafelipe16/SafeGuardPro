package com.akatsuki.safeguardpro.service.repository.remote

import com.akatsuki.safeguardpro.service.model.Epi
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EpiService {

    @GET("listarEpi")
    suspend fun getEpis(): List<Epi>

    @Multipart
    @POST("addEpi")
    suspend fun createEpi(
        @Part("nomeEpi") nomeEpi: RequestBody,
        @Part("descricao") descricao: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("validadeFabricacao") validadeFabricacao: RequestBody,
        @Part("validadeTempoUso") validadeTempoUso: RequestBody
    ): Response<Epi>

    @GET("selectEPI/{epi_id")
    suspend fun getEpiById(@Path("epi_id") id: Int): Response<List<Epi>>

    @GET("selectEpiCa/{ca}")
    suspend fun getEpiByCa(@Path("ca") ca: Int): Response<List<Epi>>

    @Multipart
    @PUT("updateEpi/{epi_id}")
    suspend fun updateEpi(
        @Path("epi_id") epiId: Int,
        @Part("nomeEpi") nomeEpi: RequestBody,
        @Part("descricao") descricao: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("validadeFabricacao") validadeFabricacao: RequestBody,
        @Part("validadeTempoUso") validadeTempoUso: RequestBody
    ): Response<Epi>

    @DELETE("deleteEpi/{epi_id}")
    suspend fun deleteEpiById(@Path("epi_id") id: Int): Response<Epi>
}