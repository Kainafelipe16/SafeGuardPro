package com.akatsuki.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akatsuki.safeguardpro.service.model.Epi
import com.akatsuki.safeguardpro.service.model.Funcionario
import com.akatsuki.safeguardpro.service.repository.EpiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpiViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EpiRepository()
    private val mEpi = MutableLiveData<Epi>()
    val epi: LiveData<Epi> = mEpi

    fun insert(epi: Epi) {
        viewModelScope.launch(Dispatchers.IO) {
//            repository.insertEpi(epi)
        }
    }

    fun getEpi(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//            mEpi.postValue(repository.getEpi(id))
        }
    }

    fun update(epi: Epi) {
        viewModelScope.launch(Dispatchers.IO) {
//            repository.update(epi)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//            repository.delete(id)
        }
    }
}