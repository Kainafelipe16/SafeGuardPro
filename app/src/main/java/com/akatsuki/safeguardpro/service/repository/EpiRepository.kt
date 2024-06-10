package com.akatsuki.safeguardpro.service.repository

import android.content.Context
import com.akatsuki.safeguardpro.service.model.Epi
import com.akatsuki.safeguardpro.service.repository.remote.EpiService
import com.akatsuki.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EpiRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(EpiService::class.java)

    private val epiEmpty = Epi(0, "", "", "", "", "")

    suspend fun getEpis(): List<Epi> {
        return mRemote.getEpis()
    }
    suspend fun getEpi(id: Int): Epi {
        val response = mRemote.getEpiById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: epiEmpty
        } else {
            epiEmpty
        }
    }

    suspend fun insertEpi(epi: Epi): Epi {
        return mRemote.createEpi(
            nomeEpi = epi.nomeEpi.toRequestBody("text/plain".toMediaTypeOrNull()),
            descricao = epi.descricao.toRequestBody("text/plain".toMediaTypeOrNull()),
            cA = epi.cA.toRequestBody("text/plain".toMediaTypeOrNull()),
            validadeFabricacao = epi.validadeFabricacao.toRequestBody("text/plain".toMediaTypeOrNull()),
            validadeTempoUso = epi.validadeTempoUso.toRequestBody("text/plain".toMediaTypeOrNull()),
        ).body() ?: epiEmpty
    }

    suspend fun updateEpi(id: Int, epi: Epi): Epi {
        return mRemote.updateEpi(
            nomeEpi = epi.nomeEpi.toRequestBody("text/plain".toMediaTypeOrNull()),
            descricao = epi.descricao.toRequestBody("text/plain".toMediaTypeOrNull()),
            cA = epi.cA.toRequestBody("text/plain".toMediaTypeOrNull()),
            validadeFabricacao = epi.validadeFabricacao.toRequestBody("text/plain".toMediaTypeOrNull()),
            validadeTempoUso = epi.validadeTempoUso.toRequestBody("text/plain".toMediaTypeOrNull()),
            epiId = id
        ).body() ?: epiEmpty
    }

    suspend fun deletePessoa(id: Int): Boolean {
        return mRemote.deleteEpiById(id).isSuccessful
    }
}