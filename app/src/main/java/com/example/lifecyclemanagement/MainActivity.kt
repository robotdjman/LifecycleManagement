package com.example.lifecyclemanagement

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    //Text Inputs

    lateinit var firstNameBox: EditText;
    lateinit var middleNameBox: EditText;
    lateinit var lastNameBox: EditText;

    //Image Button
    lateinit var imageButton: Button;

    //Image View
    lateinit var imageView: ImageView;

    //Buttons
    lateinit var saveButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup Inputs
        firstNameBox = window.findViewById(R.id.firstNameTextEdit);
        middleNameBox = window.findViewById(R.id.middleNameTextEdit);
        lastNameBox = window.findViewById(R.id.lastNameTextEdit);

        //Image
        imageButton = window.findViewById(R.id.imageButton);
        imageView = window.findViewById(R.id.imageView);
        imageButton.setOnClickListener {
            val camIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try {
                cameraLauncher.launch(camIntent);
            } catch (ex: ActivityNotFoundException) {
                //Do something
            }
        }

        //Next Button
        saveButton = window.findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            // click handling code
            if(firstNameBox.text.length > 0 && middleNameBox.text.length > 0 && lastNameBox.text.length > 0) {
                //Save info
                var nextIntent = Intent(this@MainActivity, DisplayActivity::class.java);
                nextIntent.putExtra("firstName", firstNameBox.text.toString());
                nextIntent.putExtra("lastName", lastNameBox.text.toString());
                startActivity(nextIntent);
            }
        }

    }

    private var cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == RESULT_OK) {
            var photo: Bitmap = it.data!!.extras?.get("data") as Bitmap;
            imageView.setImageBitmap(photo);
        }
    }

    //Save instance variables
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("firstName", firstNameBox.text.toString());
        outState.putString("lastName", lastNameBox.text.toString());
        outState.putString("middleName", middleNameBox.text.toString());
    }

    //Load instance variables
    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        firstNameBox.setText(savedInstanceState?.getString("firstName") ?: "");
        middleNameBox.setText(savedInstanceState?.getString("middleName") ?: "");
        lastNameBox.setText(savedInstanceState?.getString("lastName") ?: "");
    }
}