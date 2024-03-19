package com.example.recetasv2.data

import android.telecom.Call
import com.example.recetasv2.models.Receta as Receta1

interface RecetasServiceApi {
    @GET("endpoint/recetas") // Reemplaza "endpoint/recetas" con la URL real de la API
    fun getRecetas(): Call<List<Receta1>>
}

annotation class GET(val value: String)
