package com.example.water_app.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water_app.R
import com.example.water_app.databinding.ActivitySubmitBinding
import com.example.water_app.model.PostData
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory

class SubmitActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivitySubmitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        // 뷰바인딩
        binding = ActivitySubmitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //인텐트 putextra getextra 하는 부분
        val cntr_sn = intent.getStringExtra("cntr_sn")
        val cntr_ttl = intent.getStringExtra("cntr_ttl")
        val cntr_cn = intent.getStringExtra("cntr_cn")

        binding.donationTtl.text = cntr_ttl
        binding.donationCn.text = cntr_cn

        binding.btnDonation.setOnClickListener{
            val intent = Intent(this, DonationActivity::class.java)
            intent.putExtra("cntr_sn",cntr_sn)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }
    }
}