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
import android.widget.RadioButton
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

        val logDePersonaje={personaje: String->
            Log.d("Personaje","Se ha creado el personaje-> $personaje")

        }


        btnImg.setOnClickListener {

            if (nombre.text.isEmpty()) {

                nombre.error = "Tu héroe no tiene nombre"
                nombre.requestFocus()

            }else{
                val listaHab=mutableListOf<String>()
                if(Sigilo.isChecked) listaHab.add("Sigilo")
                if(Combate.isChecked) listaHab.add("Combate con Espada")
                val faccion = findViewById<RadioButton>(Facciones.checkedRadioButtonId).text.toString()

                val textoHabilidad=if(listaHab.isNotEmpty())listaHab.toString() else "Sin habilidades"

                logDePersonaje("Nombre ${nombre.text}| Raza ${Raza.selectedItem} | Habilidades: ${textoHabilidad} Faccion: ${faccion}");

                Toast.makeText(this, "El personaje ha sido creado", Toast.LENGTH_SHORT).show()
            }



        }









    }
}