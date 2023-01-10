package com.example.water_app.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.water_app.R
import com.example.water_app.databinding.ActivityNoticeContentBinding

class NoticeContentActivity : AppCompatActivity() {

    //뷰바인딩
    private lateinit var binding: ActivityNoticeContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_content)

        // 뷰바인딩
        binding = ActivityNoticeContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트 getextra
        val notice_ttl = intent.getStringExtra("notice_ttl")
        val notice_cn = intent.getStringExtra("notice_cn")

        binding.tvTitle.text = notice_ttl
        binding.tvContent.text = notice_cn
    }
}