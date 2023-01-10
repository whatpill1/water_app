package com.example.water_app.donation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.R
import com.example.water_app.databinding.ActivityDonationBinding

class DonationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)

        // 뷰바인딩
        binding = ActivityDonationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트 getextra
        val cntr_sn = intent.getStringExtra("cntr_sn")

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }
    }
}