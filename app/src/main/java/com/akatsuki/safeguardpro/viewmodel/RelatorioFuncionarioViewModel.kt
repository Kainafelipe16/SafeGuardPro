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

class RelatorioFuncionarioViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FuncionarioRepository()

    private val mFuncionarioList = MutableLiveData<List<Funcionario>>()

    val funcionarioList: LiveData<List<Funcionario>> = mFuncionarioList

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
//            mFuncionarioList.postValue(repository.getFuncionario())
        }
    }
}