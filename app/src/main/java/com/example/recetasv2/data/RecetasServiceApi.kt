package com.example.recetasv2.data

import com.example.recetasv2.models.Receta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecetasServiceApi {
    @GET("endpoint/recetas")
    suspend fun getRecetas(): List<Receta>

    @GET("search")
    suspend fun searchByName(@Query("name") query: String): Response<List<Receta>>

    @GET("{id}")
    suspend fun findById(@Path("id") identifier: String): Response<Receta>
}
