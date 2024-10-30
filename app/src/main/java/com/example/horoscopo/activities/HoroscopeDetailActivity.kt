package com.example.horoscopo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscopo.R
import com.example.horoscopo.data.HoroscopeProvider
import com.example.horoscopo.data.Horoscope
import android.widget.TextView
import android.widget.ImageView

class HoroscopeDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope_detail)

        val horoscopeId = intent.getStringExtra("HOROSCOPE_ID")
        val horoscope = HoroscopeProvider.findById(horoscopeId!!)

        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val datesTextView: TextView = findViewById(R.id.datesTextView)
        val symbolImageView: ImageView = findViewById(R.id.symbolImageView)

        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.dates)
        symbolImageView.setImageResource(horoscope.image)
    }
}