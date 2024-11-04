package com.example.horoscopo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope

class HoroscopeAdapter(
    private val horoscopes: MutableList<Horoscope>,
    private val onItemClicked: (Horoscope) -> Unit,
    private val onFavoriteClicked: (Horoscope) -> Unit
) : RecyclerView.Adapter<HoroscopeAdapter.HoroscopeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = horoscopes[position]
        holder.bind(horoscope, onItemClicked, onFavoriteClicked)
    }

    override fun getItemCount() = horoscopes.size

    inner class HoroscopeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val favoriteButton: ImageButton = itemView.findViewById(R.id.favoriteButton)

        fun bind(horoscope: Horoscope, onItemClicked: (Horoscope) -> Unit, onFavoriteClicked: (Horoscope) -> Unit) {
            nameTextView.text = itemView.context.getString(horoscope.name)
            dateTextView.text = itemView.context.getString(horoscope.dates)
            imageView.setImageResource(horoscope.image)
            itemView.setOnClickListener { onItemClicked(horoscope) }
            favoriteButton.setOnClickListener {
                onFavoriteClicked(horoscope)
                favoriteButton.setImageResource(R.drawable.corazon_lleno)
            }
        }
    }

    fun moveToFirstPosition(horoscope: Horoscope) {
        val index = horoscopes.indexOf(horoscope)
        if (index > -1) {
            horoscopes.removeAt(index)
            horoscopes.add(0, horoscope)
            //notifyItemMoved(index, 0)
            notifyDataSetChanged()
        }
    }

    fun updateData(newHoroscopes: List<Horoscope>) {
        horoscopes.clear()
        horoscopes.addAll(newHoroscopes)
        notifyDataSetChanged()
    }
}