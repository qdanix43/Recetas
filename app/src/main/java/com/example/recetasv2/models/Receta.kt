package com.example.recetasv2.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recetas")
data class Receta(
    @PrimaryKey val id: Int,
    val nombre: String,
    val descripcion: String,
    val imagenUrl: String
)
