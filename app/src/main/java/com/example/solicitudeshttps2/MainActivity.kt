package com.example.solicitudeshttps2

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity(),CompletadoListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bValidarRed = findViewById<Button>(R.id.bValidarRed)
        val bSolicitudHttp = findViewById<Button>(R.id.bSolicitudHttp)
        val bVolley = findViewById<Button>(R.id.bVolley)

        bValidarRed.setOnClickListener {
            //codigo para validar red
            if (Network.hayRed(this)){
                Toast.makeText(this,"Si hay red!!", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"No hay conexión a Internet!", Toast.LENGTH_LONG).show()
            }

        }

        bSolicitudHttp.setOnClickListener {
            //código para validar solicitud
            if (Network.hayRed(this)){
                //Log.d("bSolicitudHttp",descargarDatos("http://www.google.com"))
                DescargaURL(this).execute("http://www.google.com")
            }
            else{
                Toast.makeText(this,"No hay conexión a Internet!", Toast.LENGTH_LONG).show()
            }
        }

        bVolley.setOnClickListener {
            //código para validar solicitud
            if (Network.hayRed(this)){
                solicitudHttpVolley("http://www.google.com")
            }
            else{
                Toast.makeText(this,"No hay conexión a Internet!", Toast.LENGTH_LONG).show()
            }
        }
    }

    //Metodo para volley

    private fun solicitudHttpVolley(url:String){
        val queue = Volley.newRequestQueue(this)

        val solicitud = StringRequest(Request.Method.GET,url,Response.Listener<String> { response ->
            try {
                Log.d("SolicitudHttpVolley",response)
            } catch (e:Exception) {

            }
        },Response.ErrorListener {})

        queue.add(solicitud)
    }

    override fun descargaCompleta(Resultado: String) {
        TODO("Not yet implemented")
        Log.d("descargaCompleta",Resultado)
    }
}