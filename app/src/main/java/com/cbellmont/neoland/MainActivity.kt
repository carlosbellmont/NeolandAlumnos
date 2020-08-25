package com.cbellmont.neoland

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MainActivityViewModel::class.java)


        viewModel.isLoading.observe(this, Observer<Boolean> {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        viewModel.isError.observe(this, Observer<Boolean> {
            if (it) {
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.goNext.observe(this, Observer<Boolean> {
            if (it) {
                Toast.makeText(this, "Bievenido!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        })

        ivLogo.setImageResource(R.mipmap.logo_neoland)
        ivTexto.setImageResource(R.mipmap.texto_neoland)


        viewModel.cargarEmailGuardado()?.let {
            if (it.isNotEmpty()) {
                cbRecordar.isChecked = true
            }
            editTextTextEmailAddress.setText(it)
        }

        cbRecordar.setOnClickListener {
            if (!cbRecordar.isChecked) viewModel.borrarEmail()
        }

        bLogin.setOnClickListener {
            if (viewModel.esEmailValido(editTextTextEmailAddress.text.toString())) {
                viewModel.callSend()


            } else {
                Toast.makeText(
                    this,
                    getString(viewModel.getErrorFromEmail(editTextTextEmailAddress.text.toString())),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun showLoading() {
        loginProgressBar.visibility = View.VISIBLE

    }

    fun hideLoading() {
        loginProgressBar.visibility = View.GONE

    }
}