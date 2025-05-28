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