package com.example.event_management.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.event_management.helper.DatabaseHelper
import com.example.event_management.databinding.ActivityMainBinding

class SqliteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            dbHelper.addRecord(name)
            Toast.makeText(this, "Record Added", Toast.LENGTH_SHORT).show()
        }

        binding.btnView.setOnClickListener {
            val cursor = dbHelper.getAllRecords()
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    Toast.makeText(this, "Name: $name", Toast.LENGTH_SHORT).show()
                } while (cursor.moveToNext())
            }
        }
    }
}