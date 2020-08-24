package com.cbellmont.neoland

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG_USUARIO = "TAG_USUARIO"
    }

    // Comprobar

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


}