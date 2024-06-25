package com.akatsuki.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akatsuki.safeguardpro.service.model.Funcionario
import com.akatsuki.safeguardpro.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FuncionarioViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FuncionarioRepository(application)

    private val mCreatedFuncionario = MutableLiveData<Funcionario>()
    val createdFuncionario: LiveData<Funcionario> = mCreatedFuncionario

    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    private val mFuncionarioList = MutableLiveData<List<Funcionario>>()
    val funcionarioList: LiveData<List<Funcionario>> = mFuncionarioList

    private val mDeletedFuncionario = MutableLiveData<Boolean>()
    val deletedFuncionario: LiveData<Boolean> = mDeletedFuncionario

    private val mUpdatedPessoa = MutableLiveData<Funcionario>()
    val updatedFuncionario: LiveData<Funcionario> = mUpdatedPessoa

    private val mFuncionario = MutableLiveData<Funcionario>()
    val funcionario: LiveData<Funcionario> = mFuncionario

    fun update(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updatedFuncionario = repository.updateFuncionario(funcionario)
                mUpdatedPessoa.postValue(updatedFuncionario)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun deleteFuncionario(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedFuncionario.postValue(repository.deleteFuncionario(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mFuncionarioList.postValue(repository.getFuncionarios())
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdPessoa = repository.insertFuncionario(funcionario)
                mCreatedFuncionario.postValue(createdPessoa)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun getFuncionario(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mFuncionario.postValue(repository.getFuncionario(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun getFuncionarioByCpf(cpf: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mFuncionario.postValue(repository.getFuncionarioByCpf(cpf))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}