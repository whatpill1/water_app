package com.example.water_app.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.R
import com.example.water_app.databinding.ActivityDonationBinding
import com.example.water_app.databinding.ActivityMyPageBinding
import com.example.water_app.databinding.ActivitySubmitBinding

class DonationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)

        // 뷰바인딩
        binding = ActivityDonationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }

    }
}