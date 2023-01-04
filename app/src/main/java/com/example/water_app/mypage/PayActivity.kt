package com.example.water_app.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.water_app.R
import com.example.water_app.databinding.MypagePayBinding

class PayActivity : AppCompatActivity() {

    private lateinit var binding: MypagePayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_pay)

        binding = MypagePayBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}