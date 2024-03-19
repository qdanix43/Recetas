package com.example.recetasv2.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recetasv2.Adapters.RecetaAdapter
import com.example.recetasv2.models.Receta
import com.example.recetav2.R
import com.example.recetav2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecetaAdapter
    private var recetaList: List<Receta> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecetaAdapter(recetaList) { position ->
            onItemClickListener(position)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        binding.progress.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.emptyPlaceholder.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        initSearchView(menu?.findItem(R.id.menu_search))
        return true
    }

    private fun initSearchView(searchItem: MenuItem?) {
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrBlank()) {
                        searchReceta(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }


    private fun onItemClickListener(position: Int) {
        // Aquí puedes implementar lo que deseas hacer cuando se hace clic en un elemento del RecyclerView
    }

    private fun searchReceta(query: String) {
        // Aquí debes implementar la lógica para buscar recetas según el término de búsqueda proporcionado
    }
}
