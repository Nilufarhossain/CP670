package com.example.androidassignments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListItemsActivity : Activity() {
    private val TAG = "ListItemsActivity"
    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var imageButton: ImageButton
    private lateinit var photoURI: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)

        Log.i(TAG, "onCreate called")
        print("onCreate: ListItemsActivity started")

        val mySwitch = findViewById<Switch>(R.id.my_switch)
        mySwitch.setOnCheckedChangeListener { _, isChecked ->
            val text = if (isChecked) getString(R.string.switch_on) else getString(R.string.switch_off)
            val duration = if (isChecked) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
            Toast.makeText(this, text, duration).show()
        }

        imageButton = findViewById(R.id.imageButton)
        imageButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val checkBox = findViewById<CheckBox>(R.id.my_checkbox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AlertDialog.Builder(this)
                    .setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title)
                    .setPositiveButton(R.string.ok) { _, _ ->
                        Log.i(TAG, "User confirmed exit")
                        finish()
                        Toast.makeText(this, getString(R.string.dialog_title), Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton(R.string.cancel) { _, _ ->
                        Log.i(TAG, "User canceled exit")
                        checkBox.isChecked = false
                    }
                    .show()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(packageManager) == null) {
            Toast.makeText(this, getString(R.string.no_camera_found), Toast.LENGTH_SHORT).show()
            return
        }

        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            Log.e(TAG, "Error creating file", ex)
            null
        }

        photoFile?.also {
            photoURI = FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.fileprovider",
                it
            )
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            // Grant temporary write permission to camera app
            takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } ?: run {
            Toast.makeText(this, getString(R.string.no_camera_found), Toast.LENGTH_SHORT).show()
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                // Update image button with photo
                imageButton.setImageURI(photoURI)
            } else {
                Toast.makeText(this, getString(R.string.no_camera_found), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun print(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
