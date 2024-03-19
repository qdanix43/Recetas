import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.recetasv2.data.RecetasServiceApi
import com.example.recetasv2.databinding.ActivityMainBinding
import com.example.recetasv2.models.Receta
import com.example.recetasv2.utils.Retrofit.Call
import Retrofit.Callback
import Retrofit.Response
import Retrofit.Retrofit
import Retrofit.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recetasService: RecetasServiceApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/recipes/search?q=Margherita")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Crear una instancia de la interfaz de servicio de recetas
        recetasService = retrofit.create(RecetasServiceApi::class.java)

        // Realizar la solicitud de red para obtener las recetas
        recetasService.getRecetas().enqueue(object : Callback<List<Receta>> {
            override fun onResponse(call: Call<List<Receta>>, response: Response<List<Receta>>) {
                if (response.isSuccessful) {
                    val recetas = response.body()
                    // Aquí puedes procesar las recetas obtenidas de la API
                    recetas?.forEach { receta ->
                        Log.d("Receta", "Nombre: ${receta.nombre}, Ingredientes: ${receta.ingredientes}")
                    }
                } else {
                    Log.e("API Error", "Error al obtener las recetas: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Receta>>, t: Throwable) {
                Log.e("Network Error", "Error de red al obtener las recetas", t)
            }
        })
    }
}
