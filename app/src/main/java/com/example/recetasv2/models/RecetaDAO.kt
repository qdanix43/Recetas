package com.example.recetasv2.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecetaDao {
    @Query("SELECT * FROM Receta")
    suspend fun getAllRecetas(): List<Receta>

    @Insert
    suspend fun insertRecetas(recetas: List<Receta>)
}
