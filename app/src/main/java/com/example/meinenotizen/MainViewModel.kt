package com.example.meinenotizen

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.meinenotizen.api.UserApi
import com.example.meinenotizen.data.Notizen
import com.example.meinenotizen.data.NotizenDataBase.Companion.getDataBase
import com.example.meinenotizen.repo.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch


const val TAG = "MainVIEWMODEL"

/**
 * Das MainViewModel kümmert sich um die Kommunikation mit der Firebase Authentication
 * um einen SHA-1 Key zu generieren einfach folgene Zeilen ins Terminal kopieren
 * >>keytool -alias androiddebugkey -keystore ~/.android/debug.keystore -list -v -storepass android<<
 */

class MainViewModel (application: Application) : AndroidViewModel(application){

    // Kommunikationspunkt mit der FirebaseAuth
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val database = getDataBase(application)
    private val repository = Repository(database,UserApi)

    val notizyList = repository.notizenList

    // currentuser ist null wenn niemand eingeloggt ist
    private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    private val _complete = MutableLiveData<Boolean>()
    val complete: LiveData<Boolean>
        get() = _complete

    private val _currentImageBitmap = MutableLiveData<Bitmap>()
    val currentImageBitmap: LiveData<Bitmap>
        get() = _currentImageBitmap


    // hier wird versucht einen User zu erstellen um diesen anschließend auch gleich
    // einzuloggen
    fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                login(email, password)
            } else {
                Log.e(TAG, "SignUp failed: ${it.exception}")
            }
        }
    }
    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _currentUser.value = firebaseAuth.currentUser
            } else {
                Log.e(TAG, "Login feiled: ${it.exception}")
            }
        }
        fun logout() {
            firebaseAuth.signOut()
            _currentUser.value = firebaseAuth.currentUser
        }
    }

    //    hier wird eine Nortiz erstellt
    fun insertNotiz(notizen: Notizen) {
        viewModelScope.launch {
            repository.insert(notizen)
            _complete.value = true
        }
    }
    // hier wird ein update von einer notiz durchgeführt
    fun updateNotiz(notizen: Notizen) {
        viewModelScope.launch {
            repository.update(notizen)
            _complete.value = true
        }
    }
    //  hier kann man eine notiz löschen
    fun deleteNotiz(notizen: Notizen) {
        viewModelScope.launch {
            repository.delete(notizen)
            _complete.value = true
        }
    }

    fun unsetComplete() {
        _complete.value = false
    }

    fun setBitmap(b: Bitmap) {
        _currentImageBitmap.value = b
    }
}