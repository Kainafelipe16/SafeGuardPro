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
    private val repository = FuncionarioRepository()
    private val mFuncionario = MutableLiveData<Funcionario>()
    val funcionario: LiveData<Funcionario> = mFuncionario

    fun insert(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
//            repository.insertFuncionario(funcionario)
        }
    }

    fun getFuncionario(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//            mFuncionario.postValue(repository.getFuncionario(id))
        }
    }

    fun update(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
//            repository.update(funcionario)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//            repository.delete(id)
        }
    }
}