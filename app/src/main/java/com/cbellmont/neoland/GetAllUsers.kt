package com.cbellmont.neoland

import android.util.Log
import okhttp3.*
import java.io.IOException

class GetAllUsers {
    companion object {
        private const val URL = "https://randomuser.me/api/?results=50"

        fun send() {
            val client = OkHttpClient()
            val request = Request.Builder().url(URL).build()
            val call = client.newCall(request)
            call.enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("errorGetAllUsers", "Error cargando los usuarios")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("successGetAllUsers", "Cargando Usuarios")
                }
            })

        }
    }


}