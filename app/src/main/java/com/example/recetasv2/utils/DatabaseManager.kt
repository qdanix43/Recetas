import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.recetasv2.models.Receta

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Constantes para el nombre y la versión de la base de datos
        val DATABASE_NAME = "recetas_app.db"
        val DATABASE_VERSION = 1

        // Constantes para los nombres de las columnas de la tabla de recetas
        val COLUMN_NAME_ID = "id"
        val COLUMN_NOMBRE = "nombre"
        val COLUMN_DESCRIPCION = "descripcion"
        val COLUMN_INGREDIENTES = "ingredientes"
        val COLUMN_PASOS = "pasos"

        // Sentencia SQL para crear la tabla de recetas
        val SQL_CREATE_TABLE =
            "CREATE TABLE ${Receta.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NOMBRE TEXT," +
                    "$COLUMN_DESCRIPCION TEXT," +
                    "$COLUMN_INGREDIENTES TEXT," +
                    "$COLUMN_PASOS TEXT)"

        // Sentencia SQL para eliminar la tabla de recetas
        val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${Receta.TABLE_NAME}"
    }

    // Método llamado al crear la base de datos por primera vez
    override fun onCreate(db: SQLiteDatabase) {
        // Ejecutar la sentencia SQL para crear la tabla de recetas
        db.execSQL(SQL_CREATE_TABLE)
    }

    // Método llamado cuando se debe actualizar la base de datos
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Eliminar la tabla de recetas existente si existe
        db.execSQL(SQL_DELETE_TABLE)
        // Crear una nueva tabla de recetas
        onCreate(db)
    }

    // Método para destruir la base de datos
    fun destroyDatabase() {
        // Obtener una instancia de base de datos en modo escritura
        val db = writableDatabase
        // Ejecutar la sentencia SQL para eliminar la tabla de recetas
        db.execSQL(SQL_DELETE_TABLE)
        // Crear una nueva tabla de recetas
        onCreate(db)
    }
}
