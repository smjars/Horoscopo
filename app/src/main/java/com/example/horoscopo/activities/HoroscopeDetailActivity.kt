package com.example.horoscopo.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscopo.R
import com.example.horoscopo.data.HoroscopeProvider
import com.example.horoscopo.data.Horoscope
import android.widget.TextView
import android.widget.ImageView
import android.widget.Button

class HoroscopeDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope_detail)

        val horoscopeId = intent.getStringExtra("HOROSCOPE_ID")
        val horoscope = HoroscopeProvider.findById(horoscopeId!!)

        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val datesTextView: TextView = findViewById(R.id.datesTextView)
        val symbolImageView: ImageView = findViewById(R.id.symbolImageView)
        val messageTextView: TextView = findViewById(R.id.messageTextView)
        val backButton: Button = findViewById(R.id.backButton)

        nameTextView.setText(getString(horoscope.name))
        datesTextView.setText(getString(horoscope.dates))
        symbolImageView.setImageResource(horoscope.image)
        messageTextView.text = getString(horoscope.message)

        //aplicar animacion
        val animation = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        symbolImageView.startAnimation(animation)

        backButton.setOnClickListener{
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) { //hace lo que quiero
            R.id.menu_favorite -> {
                println("Menu favorito")
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item) //si no vuelve a la funcionalidad normal. aunque parece que no hace nada...
            }
        }
    }
}