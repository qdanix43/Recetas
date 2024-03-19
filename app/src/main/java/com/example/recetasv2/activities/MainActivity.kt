import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.util.query
import com.example.recetasv2.data.RecetasServiceApi
import com.example.recetasv2.databinding.ActivityMainBinding
import com.example.recetasv2.utils.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.List

private val Any.isSuccessful: Boolean
    get() {
        TODO("Not yet implemented")
    }

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recetasService: RecetasServiceApi
    private var RecetaList:List<RecetaList> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Retrofit y obtener una instancia de la interfaz de servicio
        recetasService = RetrofitClient.getClient().create(RecetasServiceApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano

            val response = service.searchByName(query)

            runOnUiThread {
                // Modificar UI
                binding.progress.visibility = View.GONE

                if (response.body() != null) {
                    Log.i("HTTP", "respuesta correcta :)")
                    RecetaList = response.body()?.results.orEmpty()
                    adapter.updateItems(RecetaList)

                    if (RecetaList.isNotEmpty()) {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.emptyPlaceholder.visibility = View.GONE
                    } else {
                        binding.recyclerView.visibility = View.GONE
                        binding.emptyPlaceholder.visibility = View.VISIBLE
                    }
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                    Toast.makeText(
                        this@MainActivity,
                        "Hubo un error inesperado, vuelva a intentarlo más tarde",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
