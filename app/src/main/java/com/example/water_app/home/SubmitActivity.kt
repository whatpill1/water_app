package com.example.water_app.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.R
import com.example.water_app.databinding.ActivityCommunicationBinding
import com.example.water_app.databinding.ActivitySubmitBinding

class SubmitActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivitySubmitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        // 뷰바인딩
        binding = ActivitySubmitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDonation.setOnClickListener{
            val intent = Intent(this, DonationActivity::class.java)
            startActivity(intent)
        }
    }
}