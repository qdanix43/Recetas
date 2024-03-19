package com.example.recetasv2.models


data class Receta(
    val id: Long = -1, // El ID se inicializa como -1 para indicar que es una receta nueva
    val nombre: String,
    val descripcion: String,
    val ingredientes: List<String>,
    val pasos: List<String>
) {
    companion object {
        val COLUMN_PASOS: String
            get() {
                TODO()
            }
        val COLUMN_INGREDIENTES: String
            get() {
                TODO()
            }
        val COLUMN_DESCRIPCION: String
            get() {
                TODO()
            }
        val COLUMN_NOMBRE: String
            get() {
                TODO()
            }
        val TABLE_NAME: String
            get() {
                TODO()
            }
    }
}
