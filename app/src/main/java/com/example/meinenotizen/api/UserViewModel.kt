package com.example.meinenotizen.api

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.meinenotizen.data.NotizenDataBase.Companion.getDataBase
import com.example.meinenotizen.repo.Repository
import com.firebase.ui.auth.data.model.User
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDataBase(application)
    private val repository = Repository(database,UserApi)

    val users = repository.users

    fun loadData() {
        viewModelScope.launch {
            repository.getUsers()
        }
    }

    fun addNewUser(user: User) {
        viewModelScope.launch {
            repository.createUser(user)
        }
    }
}
