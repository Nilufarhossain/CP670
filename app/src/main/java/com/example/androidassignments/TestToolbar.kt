package com.example.androidassignments

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class TestToolbar : AppCompatActivity() {

    var newMessage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_toolbar)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)

        // Floating action button (FAB) setup
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {

            Snackbar.make(it, "My Meassage!", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_one -> {
                Snackbar.make(findViewById(android.R.id.content), "You selected item 1", Snackbar.LENGTH_SHORT).show()
                true
            }

            R.id.action_two -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.dialog_title)
                builder.setPositiveButton(R.string.ok) { _, _ ->
                    finish()
                }
                builder.setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
                builder.create().show()
                true
            }

            R.id.action_three -> {
                showCustomDialog()
                true
            }

            R.id.action_about -> {
                Toast.makeText(this, "Version 1.0, by Your Name", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Do you want to go back?")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun showCustomDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
        builder.setView(dialogView)

        val editText = dialogView.findViewById<EditText>(R.id.new_message)

        builder.setPositiveButton("Ok") { _, _ ->
            newMessage = editText.text.toString()
            Snackbar.make(findViewById(android.R.id.content), "New message: $newMessage", Snackbar.LENGTH_LONG).show()
        }

        builder.setNegativeButton("Cancel", null)
        builder.show()
    }
    fun getSnackbarMessage(): String {
        return if (!newMessage.isNullOrEmpty()) newMessage!! else "No message set"
    }
}

