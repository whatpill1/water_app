package com.example.water_app.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.ActivityNoticeBinding
import com.example.water_app.databinding.ActivityPeriodBinding
import com.example.water_app.recyclerview.NoticeAdapter
import com.example.water_app.recyclerview.PeriodAdapter
import com.example.water_app.vo.NoticeData
import com.example.water_app.vo.PeriodData

class NoticeActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        // 뷰바인딩
        binding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noticeList = arrayListOf(
            NoticeData("공지사항1"),
            NoticeData("공지사항2"),
            NoticeData("공지사항3"),
            NoticeData("공지사항4"),
            NoticeData("공지사항5"),
            NoticeData("공지사항6"),
            NoticeData("공지사항7"),
            NoticeData("공지사항8"),
            NoticeData("공지사항9"),
            NoticeData("공지사항10"),
            NoticeData("공지사항11")
        )

        binding.rvNotice.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNotice.setHasFixedSize(true)        // 성능 개선
        binding.rvNotice.adapter = NoticeAdapter(noticeList)
    }
}