package com.example.event_management.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.event_management.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Comprobar si el usuario ya ha iniciado sesión
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToMainActivity()
            return
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            login(username, password)
        }
    }

    private fun login(username: String, password: String) {
        if (isValidCredentials(username, password)) {
            sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
            navigateToMainActivity()
        } else {
            val alertDialog = AlertDialog.Builder(this)
            .setTitle("Login")
            .setMessage("Invalid credentials")
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            //dialog.setCancelable(false)

            alertDialog.show()
            //Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // usuario
        return username == "ruiz" && password == "ruiz"
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
