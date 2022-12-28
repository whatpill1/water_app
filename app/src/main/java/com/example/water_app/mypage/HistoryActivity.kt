package com.example.water_app.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.ActivityHistoryBinding
import com.example.water_app.databinding.ActivityMainBinding
import com.example.water_app.recyclerview.HistoryAdapter
import com.example.water_app.vo.HistoryData

class HistoryActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // 뷰바인딩
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historyList = arrayListOf(
            HistoryData(R.drawable.red_heart, "기부1", "1,000"),
            HistoryData(R.drawable.red_heart, "기부2", "1,000"),
            HistoryData(R.drawable.red_heart, "기부3", "1,000"),
            HistoryData(R.drawable.red_heart, "기부4", "1,000")
        )

        binding.rvHistory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvHistory.setHasFixedSize(true)        // 성능 개선
        binding.rvHistory.adapter = HistoryAdapter(historyList)
    }
}