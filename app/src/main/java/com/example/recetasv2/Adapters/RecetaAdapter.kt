package com.example.recetasv2.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recetasv2.models.Receta
import com.example.recetav2.databinding.ItemRecetasBinding
import com.squareup.picasso.Picasso

class RecetaAdapter(private var items:List<Receta> = listOf(),
                       private val onClickListener: (position:Int) -> Unit
) : RecyclerView.Adapter<RecetaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val binding = ItemRecetasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecetaViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener { onClickListener(position) }
    }

    fun updateItems(results: List<Receta>) {
        items = results
        notifyDataSetChanged()
    }
}


class RecetaViewHolder(val binding: ItemRecetasBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(superhero: Receta) {
        binding.nameTextView.text = superhero.name
        Picasso.get().load(superhero.image.url).into(binding.photoImageView)
    }

}