package com.example.laboratorio2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val nombre = findViewById<EditText>(R.id.usuarioText)
        val Raza = findViewById<Spinner>(R.id.spinnerRaza)
        val Facciones = findViewById<RadioGroup>(R.id.selectFaciones)
        val Sigilo = findViewById<CheckBox>(R.id.sigilo)
        val Combate = findViewById<CheckBox>(R.id.combate)
        val btnImg = findViewById<ImageButton>(R.id.boton)
        //Esto lo que hace es para que el nombre tenga el focus
        nombre.requestFocus()

        Raza.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id:Long){
                val razaSeleccionada=parent?.getItemAtPosition(position).toString()
                Log.d("Personaje","Raza seleccionada: ${razaSeleccionada}")

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        Facciones.setOnCheckedChangeListener{group,checkId->
            when (checkId)
            {
                R.id.comunidadAnillo -> {
                    Log.d("Personaje", "Facción seleccionada: Comunidad del Anillo")
                }
                R.id.mordor->{
                    Log.d("Personaje", "Facción seleccionada: Huestes de Mordor")

                }
            }

        }
        Sigilo.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked){

                Log.d("Personaje:","Se ha activado la habilidad de : Sigilo")

            }else{
                Log.d("Personaje:","Se ha desactivado la habilidad de : Sigilo")

            }


        }
        Combate.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked)
            {
                Log.d("Personaje:","Se ha activado la habilidad de : Combate con Espada ")
            }else{
                Log.d("Personaje:","Se ha desactivado la habilidad de : Combate con Espada ")
            }


        }
        // 1. Validar si el campo de nombre está vacío al pulsar el botón
        if (nombre.text.isEmpty()) {
            // Esto hace que aparezca el icono de alerta (!) y el globo de texto con el error en la pantalla
            nombre.error = "Tu héroe no tiene nombre"

            // Esto mueve el cursor automáticamente al campo del nombre para que el usuario lo vea
            nombre.requestFocus()

            // Un aviso extra en la pantalla

        }
        btnImg.setOnClickListener {



            // 2. Si el nombre SÍ está lleno, el código continúa de forma normal:
            Toast.makeText(this, "El personaje ha sido creado", Toast.LENGTH_SHORT).show()
        }









    }
}