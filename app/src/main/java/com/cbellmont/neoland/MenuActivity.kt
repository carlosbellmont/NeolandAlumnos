package com.cbellmont.neoland

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        CoroutineScope(Dispatchers.IO).launch {
            var lista : List<User>
            withContext(Dispatchers.IO) {
                lista = App.getDatabase(application).userDao().getAll()
            }
            withContext(Dispatchers.Main) {
                lista.forEach {
                    textView.append(it.name.first + it.bootcampId + "\n")
                }
            }
        }
    }
}