package com.cbellmont.neoland

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    companion object {
        const val TAG_USUARIO = "TAG_USUARIO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MainActivityViewModel::class.java)

        ivLogo.setImageResource(R.mipmap.logo_neoland)
        ivTexto.setImageResource(R.mipmap.texto_neoland)


        viewModel.cargarEmailGuardado()?.let {
            if (it.isNotEmpty()){
                cbRecordar.isChecked = true
            }
            editTextTextEmailAddress.setText(it)
        }

        cbRecordar.setOnClickListener {
            if (!cbRecordar.isChecked) viewModel.borrarEmail()
        }

        bLogin.setOnClickListener {
            if (viewModel.esEmailValido(editTextTextEmailAddress.text.toString())) {
                Toast.makeText(this, "Todo correcto", Toast.LENGTH_LONG).show()

            } else {
               Toast.makeText(this,  viewModel.getErrorFromEmail(editTextTextEmailAddress.text.toString()), Toast.LENGTH_LONG).show()
            }

        }
    }
}