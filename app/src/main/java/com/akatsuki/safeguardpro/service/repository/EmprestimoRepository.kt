package com.akatsuki.safeguardpro.service.repository

import android.content.Context
import com.akatsuki.safeguardpro.service.model.Emprestimo
import com.akatsuki.safeguardpro.service.repository.remote.EmprestimoService
import com.akatsuki.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EmprestimoRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(EmprestimoService::class.java)
    private val emprestimoEmpty = Emprestimo(0, "", 0, 0)

    suspend fun insertEmprestimo(emprestimo: Emprestimo): Emprestimo {
        return mRemote.createEmprestimo(
            emprestimo_id = emprestimo.emprestimo_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            dataEmprestimo = emprestimo.dataEmprestimo.toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_fk = emprestimo.funcionario_fk.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            epi_fk = emprestimo.epi_fk.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
        ).body() ?: emprestimoEmpty
    }

    suspend fun getEmprestimo(id: Int): Emprestimo {
        val response = mRemote.getEmprestimoById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: emprestimoEmpty
        } else {
            emprestimoEmpty
        }
    }

    suspend fun updateEmprestimo(id: Int, emprestimo: Emprestimo): Emprestimo {
        return mRemote.updateEmprestimo(
            dataEmprestimo = emprestimo.dataEmprestimo.toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_fk = emprestimo.funcionario_fk.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            epi_fk = emprestimo.epi_fk.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            emprestimo_id = id
        ).body() ?: emprestimoEmpty
    }

    suspend fun deleteEmprestimo(id: Int): Boolean {
        return mRemote.deleteEmprestimoById(id).isSuccessful
    }

    suspend fun getEmprestimos(): List<Emprestimo> {
        return mRemote.getEmprestimos()
    }
}