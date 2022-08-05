package com.example.meinenotizen.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meinenotizen.api.UserApi
import com.example.meinenotizen.data.Notizen
import com.example.meinenotizen.data.NotizenDataBase
import com.firebase.ui.auth.data.model.User


const val TAG = "Repository"

class Repository (private val database: NotizenDataBase, private val api: UserApi){

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    suspend fun getUsers() {
        try {
            _users.value = api.retrofitService.getUsers()
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }

    suspend fun createUser(user: User) {
        try {
            api.retrofitService.createUser(user)
        } catch (e: Exception) {
            Log.e(TAG, "Error putting Data on API: $e")
        }
    }

    val notizenList: LiveData<List<Notizen>> = database.notizenDao().getAll()

    suspend fun insert(notizy: Notizen) {
        try {
            database.notizenDao().insert(notizy)
        } catch (e: Exception) {
            Log.d(TAG, "Failed to insert into Database: $e")
        }
    }
    suspend fun update(notizy: Notizen) {
        try {
            database.notizenDao().update(notizy)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to update Database: $e")
        }
    }

    suspend fun delete(notizy: Notizen) {
        try {
            database.notizenDao().deleteById(notizy.id!!.toLong())
        } catch (e: Exception) {
            Log.e(TAG, "Failed to delete from Database: $e")
        }
    }
}