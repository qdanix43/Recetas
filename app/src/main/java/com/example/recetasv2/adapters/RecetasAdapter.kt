import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recetasv2.databinding.ItemRecetasBinding

private val Any.url: Any
    get() {
        TODO("Not yet implemented")
    }
private var Nothing?.text: Any
    get() {
        TODO("Not yet implemented")
    }
    set() {}

class RecetasAdapter(
    private var items: List<Receta> = listOf(),
    private val onClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<RecetasAdapter.RecetasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetasViewHolder {
        val binding = ItemRecetasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecetasViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecetasViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { onClickListener(position) }
    }

    fun updateItems(results: List<Receta>) {
        items = results
        notifyDataSetChanged()
    }

    inner class RecetasViewHolder(private val binding: ItemRecetasBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(receta: Receta) {
            binding.apply {
                    val nameTextView = null
                nameTextView.text = receta.name
                // Asigna la imagen de la receta utilizando Picasso u otra biblioteca de carga de imágenes
                val photoImageView = null
                photoImageView?.let { Picasso.get().load(receta.image.url).into(it) }
            }
        }
    }
}

private fun Any.into(photoImageView: Any) {
    TODO("Not yet implemented")
}

private fun Any.load(url: Any): Any {
    TODO("Not yet implemented")
}

class Picasso {
    companion object {
        fun get(): Any {
            TODO("Not yet implemented")
        }
    }

}
