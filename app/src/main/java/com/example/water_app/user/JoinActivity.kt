package com.example.water_app.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.water_app.R
import com.example.water_app.databinding.ActivityJoinBinding
import com.example.water_app.model.JoinData

class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}