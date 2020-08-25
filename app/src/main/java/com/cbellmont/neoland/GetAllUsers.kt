package com.cbellmont.neoland

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class GetAllUsers {
    companion object {
        private const val URL = "https://randomuser.me/api/?results=50"

        fun send(modelView : MainActivityViewModel) {
            val client = OkHttpClient()
            val request = Request.Builder().url(URL).build()
            val call = client.newCall(request)
            call.enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    modelView.errorLoadingData()
                    Log.e("errorGetAllUsers", "Error cargando los usuarios")
                }
                override fun onResponse(call: Call, response: Response) {
                    Log.d("successGetAllUsers", "Cargando Usuarios")
                    CoroutineScope(Dispatchers.IO).launch {
                        val body = response.body?.string()
                        body?.let {
                            val jsonBody = JSONObject(body)
                            val savedResults = jsonBody.optJSONArray("results")
                            savedResults?.let {
                                val gson = Gson()
                                // indicamos de que tipo son los datos que se van a leer
                                val itemType = object : TypeToken<List<User>>(){}.type
                                // fromJson es una lsita de usuarios y savedResults debe ser String
                                val usersList = gson.fromJson<List<User>>(savedResults.toString(), itemType)
                                modelView.saveInDataBase(usersList)
                            }
                        }
                    }

                }
            })

        }
    }


}