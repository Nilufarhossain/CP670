package com.example.androidassignments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : Activity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val myButton = findViewById<Button>(R.id.button)
        myButton.setOnClickListener {
            val intent = Intent(this, ListItemsActivity::class.java)
            startActivityForResult(intent, 10)
        }
        Log.i(TAG, "onCreate called")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.start_chat_button)
        button.setOnClickListener {
            Log.i("MainActivity", "User clicked Start Chat")

            val startChatButton: Button = findViewById(R.id.start_chat_button)

            startChatButton.setOnClickListener {
                Log.i(TAG, "User clicked Start Chat")

                val intent = Intent(this, activity_chat_window::class.java)
                startActivity(intent)
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10) {
            if (resultCode == Activity.RESULT_OK) {
                val messagePassed = data?.getStringExtra("Response")

                Toast.makeText(
                    this,
                    "ListItemsActivity passed: $messagePassed",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                Toast.makeText(
                    this,
                    "Result canceled or failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    fun getNavigationTarget(action: String): Class<*>? {
        return when (action) {
            "navigate" -> ListItemsActivity::class.java
            "chat" -> activity_chat_window::class.java
            else -> null
        }
    }
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy called")
    }
}