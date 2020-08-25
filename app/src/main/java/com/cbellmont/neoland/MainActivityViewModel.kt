package com.cbellmont.neoland

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val mutableIsLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isLoading: LiveData<Boolean> get() = mutableIsLoading

    private val mutableIsError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isError: LiveData<Boolean> get() = mutableIsError

    private val mutableGoNext: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val goNext: LiveData<Boolean> get() = mutableGoNext

    companion object {
        const val TAG_USUARIO = "TAG_USUARIO"
    }

    // Comprobar Email

    fun cargarEmailGuardado(): String? {
        val sharedPref = getApplication<Application>().getSharedPreferences(
            "preferencias.txt",
            Context.MODE_PRIVATE
        )
        return sharedPref.getString(TAG_USUARIO, "")
    }

    fun borrarEmail() {
        guardarEmail("")
    }

    fun guardarEmail(email: String) {
        val sharedPref = getApplication<Application>().getSharedPreferences(
            "preferencias.txt",
            Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putString(TAG_USUARIO, email)
            commit()
        }
    }

    fun esEmailValido(email: String): Boolean {
        // if resumido
        return email.isNotEmpty() && email.contains(".") && email.contains("@")
    }

    fun getErrorFromEmail(email: String): Int {

        return if (email.isEmpty()) {
            R.string.maintext_email_not_empty
        } else if (!email.contains("@")) {
            R.string.maintext_email_with_at
        } else {
            R.string.maintext_email_with_dot
        }

    }

    fun callSend() {
        CoroutineScope(Dispatchers.IO).launch {
            // ejecutar primero lo que ocurre en main para que se muestre el loading view
            withContext(Dispatchers.Main) {
                mutableIsLoading.value = true
                mutableIsError.value = false
                mutableGoNext.value = false
            }
            withContext(Dispatchers.IO){
                GetAllUsers.send(this@MainActivityViewModel)
            }
        }
    }

    fun saveInDataBase(list: List<User>) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                App.getDataBase(getApplication()).userDao().insertAll(list)
            }
            withContext(Dispatchers.Main) {
                mutableIsLoading.value = false
                mutableGoNext.value = true
            }
        }
    }

    fun errorLoadingData() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                mutableIsLoading.value = false
                mutableIsError.value = true
            }
        }
    }


}