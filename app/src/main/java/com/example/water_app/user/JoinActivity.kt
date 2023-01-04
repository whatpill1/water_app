package com.example.water_app.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.water_app.R
import com.example.water_app.databinding.ActivityJoinBinding
import com.example.water_app.model.JoinData
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding
    private lateinit var parser: JsonParser
    private lateinit var obj: JsonObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

        val id = binding.edtId.text.toString()
        val pass = binding.edtPass.text.toString()

        val userData = JoinData(
//            binding.edtId.text.toString(),
//            binding.edtPass.text.toString()
            id, pass
        )

        binding.btnJoin.setOnClickListener {
            val retrofitWork = RetrofitWork(userData)
            retrofitWork.work()
        }
    }
}