import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.recetasv2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity(), Parcelable {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: RecetasAdapter
    private var superheroList:List<Receta> = listOf()

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.main_menu, menu)

            initSearchView(menu?.findItem(R.id.menu_search))

            return true
        }

        private fun initSearchView(searchItem: MenuItem?) {
            if (searchItem != null) {
                var searchView = searchItem.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchSuperheroes(query!!)
                        searchView.clearFocus()
                        return true
                    }

                    override fun onQueryTextChange(query: String?): Boolean {
                        return false
                    }
                })
            }
        }

        private fun onItemClickListener(position:Int) {
            val superhero: Superhero = superheroList[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, superhero.id)
            intent.putExtra(DetailActivity.EXTRA_NAME, superhero.name)
            intent.putExtra(DetailActivity.EXTRA_IMAGE, superhero.image.url)
            startActivity(intent)
            //Toast.makeText(this, getString(horoscope.name), Toast.LENGTH_LONG).show()
        }

        private fun searchSuperheroes(query: String) {
            binding.progress.visibility = View.VISIBLE

            val service: RecetasServiceApi = RetrofitProvider.getRetrofit()

            CoroutineScope(Dispatchers.IO).launch {
                // Llamada en segundo plano
                val response = service.searchByName(query)

                runOnUiThread {
                    // Modificar UI
                    binding.progress.visibility = View.GONE

                    if (response.body() != null) {
                        Log.i("HTTP", "respuesta correcta :)")
                        superheroList = response.body()?.results.orEmpty()
                        adapter.updateItems(superheroList)

                        if (superheroList.isNotEmpty()) {
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.emptyPlaceholder.visibility = View.GONE
                        } else {
                            binding.recyclerView.visibility = View.GONE
                            binding.emptyPlaceholder.visibility = View.VISIBLE
                        }
                    } else {
                        Log.i("HTTP", "respuesta erronea :(")
                        Toast.makeText(this@MainActivity, "Hubo un error inesperado, vuelva a intentarlo más tarde", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }