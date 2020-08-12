package com.cbellmont.neoland

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG_USUARIO = "TAG_USUARIO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivLogo.setImageResource(R.mipmap.logo_neoland)
        ivTexto.setImageResource(R.mipmap.texto_neoland)

        cargarPreferencias()?.let {
            if (it.isNotEmpty()){
                cbRecordar.isChecked = true
            }
            editTextTextEmailAddress.setText(it)
        }

        cbRecordar.setOnClickListener {
            if (!cbRecordar.isChecked) borrarPreferencias()
        }

        bLogin.setOnClickListener {
            if (editTextTextEmailAddress.text.isEmpty()){
                Toast.makeText(this, "El email no debe estar vacio", Toast.LENGTH_LONG).show()
            } else if (!editTextTextEmailAddress.text.contains("@") || !editTextTextEmailAddress.text.contains(".")) {
                Toast.makeText(this, "El email debe contener una @ y un .", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Todo correcto", Toast.LENGTH_LONG).show()
                if (cbRecordar.isChecked){
                    guardarPreferencias(editTextTextEmailAddress.text.toString())
                }
                // No debes poner un else borrarPreferencias() ya que de ese modo solamente sería posible borrar el email poniendo otro email correcto.
            }
        }
    }

    private fun borrarPreferencias(){
        guardarPreferencias("")
    }

    private fun cargarPreferencias() : String? {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(TAG_USUARIO, "")
    }

    private fun guardarPreferencias(usuario : String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(TAG_USUARIO, usuario)
            commit()
        }
    }
}