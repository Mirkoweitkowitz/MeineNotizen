package com.example.meinenotizen.notizen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.meinenotizen.api.UserApi
import com.example.meinenotizen.data.Notizen
import com.example.meinenotizen.data.NotizenDataBase.Companion.getDataBase
import com.example.meinenotizen.repo.Repository
import kotlinx.coroutines.launch
/**
 * Das ViewModel des NotizFragment Fragments
 */

class NotizenViewModel (application: Application) : AndroidViewModel(application) {

    private val database = getDataBase(application)
    private val repository = Repository(database,UserApi)

    private val _complete = MutableLiveData<Boolean>()
    val complete: LiveData<Boolean>
        get() = _complete

    val notizenList = repository.notizenList


    //    hier wird eine Nortiz erstellt

    fun insert(notizen: Notizen) {
        viewModelScope.launch {
            repository.insert(notizen)
            _complete.value = true
        }
    }

    // hier wird ein update von einer notiz durchgeführt

    fun update(notizen: Notizen) {
        viewModelScope.launch {
            repository.update(notizen)
            _complete.value = true
        }
    }

    //  hier kann man eine notiz löschen

    fun delete(notizen: Notizen) {
        viewModelScope.launch {
            repository.delete(notizen)
            _complete.value = true
        }
    }
    fun unsetComplete() {
        _complete.value = false
    }
    fun navigatToFragmentNotizen() {
    }
    fun resetAllValues() {
    }

}