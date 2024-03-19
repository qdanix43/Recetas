package com.example.recetasv2.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.recetasv2.data.RecetasServiceApi
import com.example.recetasv2.models.Receta
import com.example.recetasv2.utils.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "RECETA_ID"
        const val EXTRA_NAME = "RECETA_NAME"
        const val EXTRA_IMAGE = "RECETA_IMAGE"
    }

    private var recetaId: String? = null
    private lateinit var receta: Receta
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        recetaId = intent.getStringExtra(EXTRA_ID)
        val name = intent.getStringExtra(EXTRA_NAME)
        val image = intent.getStringExtra(EXTRA_IMAGE)

        binding.toolbarLayout.title = name
        Picasso.get().load(image).into(binding.photoImageView)

        findRecetaById(recetaId!!)
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun findRecetaById(id: String) {
        binding.content.progress.visibility = View.VISIBLE

        val service: RecetasServiceApi = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.findById(id)

            runOnUiThread {
                binding.content.progress.visibility = View.GONE
                if (response.isSuccessful) {
                    Log.i("HTTP", "respuesta correcta :)")
                    receta = response.body()!!
                    loadData()
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                }
            }
        }
    }

    private fun loadData() {
        binding.toolbarLayout.title = receta.name

        // Aquí debes cargar los datos de la receta en tus vistas correspondientes, por ejemplo:
        // binding.content.realNameTextView.text = receta.realName
        // binding.content.publisherTextView.text = receta.publisher
        // binding.content.placeOfBirthTextView.text = receta.placeOfBirth
        // binding.content.alignmentTextView.text = receta.alignment.uppercase()
        // ...

        // No olvides manejar los casos en los que algún dato pueda ser null
    }
}
