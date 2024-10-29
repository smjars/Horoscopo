package com.example.horoscopo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope

class HoroscopeAdapter(val items: List<Horoscope>): RecyclerView.Adapter<HoroscopeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope,parent,false)
        return HoroscopeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = items[position]
        holder.render(horoscope)
    }

}

class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view){
    var nameTextView: TextView = view.findViewById(R.id.nameTextView)
    var datesTextView: TextView = view.findViewById(R.id.dateTextView)
    var symbolImageView: ImageView = view.findViewById(R.id.imageView)

    fun render(horoscope: Horoscope){
        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.dates)
        symbolImageView.setImageResource(horoscope.image)
    }
}