package com.example.water_app.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.water_app.R
import com.example.water_app.databinding.ActivityPayBinding
import com.example.water_app.databinding.ActivityPeriodBinding

class PayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        binding = ActivityPayBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}