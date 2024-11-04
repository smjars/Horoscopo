package com.example.horoscopo.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.R
import com.example.horoscopo.adapters.HoroscopeAdapter
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider

class ListActivity : AppCompatActivity() {
    private lateinit var horoscopeList: MutableList<Horoscope>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HoroscopeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        horoscopeList = HoroscopeProvider.findAll().toMutableList()
        adapter = HoroscopeAdapter(horoscopeList,
            onItemClicked = { horoscope ->
                val intent = Intent(this, HoroscopeDetailActivity::class.java).apply {
                    putExtra("HOROSCOPE_ID", horoscope.id)
                }
                startActivity(intent)
            },
            onFavoriteClicked = { horoscope ->
                adapter.moveToFirstPosition(horoscope)
            }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val searchEditText: EditText = findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // No se usa
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se usa
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList = HoroscopeProvider.searchHoroscopes(s.toString())
                adapter.updateData(filteredList)
            }
        })
    }
}