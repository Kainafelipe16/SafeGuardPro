package com.akatsuki.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akatsuki.safeguardpro.service.model.Epi
import com.akatsuki.safeguardpro.service.repository.EpiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RelatorioEpiViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = EpiRepository()

    private val mEpiList = MutableLiveData<List<Epi>>()

    val epiList: LiveData<List<Epi>> = mEpiList

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
//            mEpiList.postValue(repository.getEpi())
        }
    }
}