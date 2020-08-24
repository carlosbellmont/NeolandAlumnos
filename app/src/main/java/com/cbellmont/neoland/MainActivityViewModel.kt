package com.cbellmont.neoland

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    // COmprobar

    fun cargarEmailGuardado(): String? {
        val sharedPref = getApplication<Application>().getSharedPreferences(
            "preferencias.txt",
            Context.MODE_PRIVATE
        )
        return sharedPref.getString(MainActivity.TAG_USUARIO, "")
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
            putString(com.cbellmont.neoland.MainActivity.TAG_USUARIO, email)
            commit()
        }
    }

    fun esEmailValido(email: String): Boolean {
        // if resumido
        return email.isNotEmpty() && email.contains(".") && email.contains("@")
    }

    fun getErrorFromEmail(email: String): String {

        return if (email.isEmpty()) {
            "El email no puede estar vacio"
        } else if (!email.contains("@")) {
            "El email debe contener un \"@\""
        } else {
            "El email debe contener un \".\""
        }

    }


}