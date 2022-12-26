package com.example.water_app.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.R

class SubmitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        val btnDonation = findViewById<Button>(R.id.btnDonation)

        btnDonation.setOnClickListener{
            val intent = Intent(this, DonationActivity::class.java)
            startActivity(intent)
        }
    }
}