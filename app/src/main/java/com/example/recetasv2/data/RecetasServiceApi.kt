package com.example.recetasv2.data

import com.example.recetasv2.models.Receta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecetasServiceApi {
    @GET("endpoint/recetas") // Reemplaza "endpoint/recetas" con la URL real de la API
    fun getRecetas(): List<Receta>


    @GET("search/{name}")
    suspend fun searchByName(@Path("name") query:String) : Response<RecetaResponse>

    @GET("{id}")
    suspend fun findById(@Path("id") identifier:String) : Response<Receta>
}
