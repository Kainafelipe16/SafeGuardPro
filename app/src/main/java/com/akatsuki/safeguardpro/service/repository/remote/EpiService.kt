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

    @GET("selectEpis")
    suspend fun getEpis(): List<Epi>

    @Multipart
    @POST("addEpi")
    suspend fun createEpi(
        @Part("nome") nomeEpi: RequestBody,
        @Part("descricao") descricao: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("validade") validadeFabricacao: RequestBody,
        @Part("tempo_uso") validadeTempoUso: RequestBody
    ): Response<Epi>

    @GET("selectEpiById/{id}")
    suspend fun getEpiById(@Path("id") id: Int): Response<List<Epi>>

    @GET("selectEpiByCa/{ca}")
    suspend fun getEpiByCa(@Path("ca") ca: Int): Response<List<Epi>>

    @Multipart
    @PUT("updateEpiById/{id}")
    suspend fun updateEpi(
        @Path("id") epiId: Int,
        @Part("nome") nomeEpi: RequestBody,
        @Part("descricao") descricao: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("validade") validadeFabricacao: RequestBody,
        @Part("tempo_uso") validadeTempoUso: RequestBody
    ): Response<Epi>

    @DELETE("deleteEpiById/{epi_id}")
    suspend fun deleteEpiById(@Path("epi_id") id: Int): Response<Epi>
}