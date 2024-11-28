package com.example.event_management.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.event_management.R
import com.example.event_management.adapters.ItemAdapter
import com.example.event_management.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.database.Cursor
import com.example.event_management.database.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var dbHelper: DatabaseHelper

    private val itemList = mutableListOf<String>()

    private lateinit var createItemLauncher: ActivityResultLauncher<Intent>
    private lateinit var editItemLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            dbHelper.addEvent(name)
            Toast.makeText(this, "Evento Agregado", Toast.LENGTH_SHORT).show()
        }

        binding.btnView.setOnClickListener {
            val cursor = dbHelper.getAllEvents()
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    Toast.makeText(this, "Evento: $name", Toast.LENGTH_SHORT).show()
                } while (cursor.moveToNext())
            }
        }

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        if (!sharedPreferences.getBoolean("isLoggedIn", false)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        setupRecyclerView()
        setupAddItemButton()

        loadItems()

        createItemLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val newItem = data?.getStringExtra("NEW_ITEM")
                newItem?.let {
                    itemList.add(it)
                    saveItems()
                    adapter.notifyItemInserted(itemList.size - 1)
                    Toast.makeText(this, "Nuevo Evento Agregado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        editItemLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val updatedItem = data?.getStringExtra("UPDATED_ITEM")
                updatedItem?.let {
                    val originalItem = data.getStringExtra("ITEM_NAME")
                    val index = itemList.indexOf(originalItem)
                    if (index != -1) {
                        itemList[index] = updatedItem
                        saveItems()
                        adapter.notifyItemChanged(index)
                        Toast.makeText(this, "Evento Actualizado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                startCreateItemActivity()
                true
            }
            R.id.action_login -> {
                openLoginActivity()
                true
            }
            R.id.action_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter(itemList,
            onItemClick = { item ->
                startEditItemActivity(item)
            },
            onDeleteClick = { position ->
                deleteItem(position)
            }
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupAddItemButton() {
        /*binding.textInputLayout.setOnClickListener {
            startCreateItemActivity()
        }*/
    }

    private fun logout() {
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
        openLoginActivity()
    }

    private fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun startCreateItemActivity() {
        val intent = Intent(this, CreateItemActivity::class.java)
        createItemLauncher.launch(intent)
    }

    private fun startEditItemActivity(item: String) {
        val intent = Intent(this, EditItemActivity::class.java)
        intent.putExtra("ITEM_NAME", item)
        editItemLauncher.launch(intent)
    }

    private fun deleteItem(position: Int) {
        itemList.removeAt(position)
        saveItems()
        adapter.notifyItemRemoved(position)
        Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()
    }

    private fun saveItems() {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(itemList)
        editor.putString("itemList", json)
        editor.apply()
    }

    private fun loadItems() {
        val gson = Gson()
        val json = sharedPreferences.getString("itemList", null)
        val type = object : TypeToken<MutableList<String>>() {}.type
        if (json != null) {
            val savedItemList: MutableList<String> = gson.fromJson(json, type)
            itemList.clear()
            itemList.addAll(savedItemList)
            adapter.notifyDataSetChanged()
        }
    }
}
