package com.example.recetasv2.data

import android.telecom.Call
import com.example.recetasv2.models.Receta
import retrofit2.Call

interface RecetasServiceApi {
    @GET("endpoint/recetas") // Reemplaza "endpoint/recetas" con la URL real de la API
    fun getRecetas(): Call<List<Receta>>
}

annotation class GET(val value: String)
