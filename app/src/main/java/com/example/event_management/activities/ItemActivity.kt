package com.example.event_management.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.event_management.databinding.ActivityItemBinding

class CreateItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val newItem = binding.etNewItem.editText?.text.toString()
            if (newItem.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("NEW_ITEM", newItem)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
                Toast.makeText(this, "Evento Guardado", Toast.LENGTH_SHORT).show()
            } else {
                binding.etNewItem.error = "La tarea no puede estar vacia"
            }
        }
    }
}