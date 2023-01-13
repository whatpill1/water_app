package com.example.water_app.donation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.water_app.R
import com.example.water_app.databinding.ActivityCommunicationBinding
import com.example.water_app.databinding.ActivityStoryBinding
import com.example.water_app.user.MySharedPreferences

class StoryActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        // 뷰바인딩
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 글 내용
        val mlrd_ttl = this.intent.extras!!.getString("mlrd_ttl")
        val mlrd_cn = this.intent.extras!!.getString("mlrd_cn")

//        // 댓글
//        val mbr_sn = MySharedPreferences.getUserSn(this).toInt()
//        val mlrd_sn = this.intent.extras!!.getInt("mlrd_sn")
//        val comt_cn = binding!!.edtComment.text.toString()

        binding.tvTitle.text = mlrd_ttl
        binding.tvContent.text = mlrd_cn
    }
}