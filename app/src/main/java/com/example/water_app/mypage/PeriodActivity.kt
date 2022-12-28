package com.example.water_app.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.ActivityHistoryBinding
import com.example.water_app.databinding.ActivityMainBinding
import com.example.water_app.databinding.ActivityPeriodBinding
import com.example.water_app.recyclerview.HistoryAdapter
import com.example.water_app.recyclerview.PeriodAdapter
import com.example.water_app.vo.HistoryData
import com.example.water_app.vo.PeriodData

class PeriodActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityPeriodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_period)

        // 뷰바인딩
        binding = ActivityPeriodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val periodList = arrayListOf(
            PeriodData(R.drawable.red_heart, "기부1", "1,000", "2022-12-01", "2022-12-28"),
            PeriodData(R.drawable.red_heart, "기부2", "1,000", "2022-12-01", "2022-12-28"),
            PeriodData(R.drawable.red_heart, "기부3", "1,000", "2022-12-01", "2022-12-28"),
            PeriodData(R.drawable.red_heart, "기부4", "1,000", "2022-12-01", "2022-12-28")
        )

        binding.rvPeriod.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvPeriod.setHasFixedSize(true)        // 성능 개선
        binding.rvPeriod.adapter = PeriodAdapter(periodList)
    }
}