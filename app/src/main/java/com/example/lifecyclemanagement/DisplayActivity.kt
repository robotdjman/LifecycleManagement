package com.example.lifecyclemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {
    lateinit var textDisplay: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        //Load user information
        var firstName: String? = intent.getStringExtra("firstName");
        var lastName: String? = intent.getStringExtra("lastName");

        //Get TextView
        textDisplay = window.findViewById(R.id.textView);

        if(firstName != null && lastName != null) {
            textDisplay.setText("${firstName} ${lastName} is logged in!")
        }
    }
}