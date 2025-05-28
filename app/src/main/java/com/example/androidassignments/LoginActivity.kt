package com.example.androidassignments
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : Activity() {
    private val TAG = "LoginActivity"
    private lateinit var emailField: EditText
    private lateinit var loginButton: Button
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        Log.i(TAG,  "onCreate called")
        emailField = findViewById(R.id.editTextEmail)
        loginButton = findViewById(R.id.button2)
//
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
//
        val savedEmail = sharedPreferences.getString("DefaultEmail", "email@domain.com")
        emailField.setText(savedEmail)

        loginButton.setOnClickListener {
            saveEmailAndLogin()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun saveEmailAndLogin() {
        val email = emailField.text.toString()
        val editor = sharedPreferences.edit()
        editor.putString("DefaultEmail", email)
        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    override fun onStart() {
        super.onStart()
        Log.i(TAG,  "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,  "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,  "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,  "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,  "onDestroy called")
    }


}