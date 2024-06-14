package com.akatsuki.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akatsuki.safeguardpro.service.model.Emprestimo
import com.akatsuki.safeguardpro.service.repository.EmprestimoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmprestimoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EmprestimoRepository(application)

    private val mCreatedEmprestimo = MutableLiveData<Emprestimo>()
    val createEmprestimo: LiveData<Emprestimo> = mCreatedEmprestimo

    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    private val mEmprestimoList = MutableLiveData<List<Emprestimo>>()
    val emprestimoList: LiveData<List<Emprestimo>> = mEmprestimoList

    private val mDeletedEmprestimo = MutableLiveData<Boolean>()
    val deleteEmprestimo: LiveData<Boolean> = mDeletedEmprestimo

    private val mUpdatedEmprestimo = MutableLiveData<Emprestimo>()
    val updateEmprestimo: LiveData<Emprestimo> = mUpdatedEmprestimo

    private val mEmprestimo = MutableLiveData<Emprestimo>()
    val emprestimo: LiveData<Emprestimo> = mEmprestimo

    fun update(emprestimo: Emprestimo) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updateEmprestimo = repository.updateEmprestimo(emprestimo)
                mUpdatedEmprestimo.postValue(updateEmprestimo)
            } catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedEmprestimo.postValue(repository.deleteEmprestimo(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mEmprestimoList.postValue(repository.getEmprestimos())
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(emprestimo: Emprestimo) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdEmprestimo = repository.insertEmprestimo(emprestimo)
                mCreatedEmprestimo.postValue(createdEmprestimo)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun getEmprestimo(id: Int) {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                mEmprestimo.postValue(repository.getEmprestimo(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}