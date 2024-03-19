package com.example.recetasv2.models


data class Receta(
    val id: Long = -1, // El ID se inicializa como -1 para indicar que es una receta nueva
    val nombre: String,
    val descripcion: String,
    val ingredientes: List<String>,
    val pasos: List<String>
)