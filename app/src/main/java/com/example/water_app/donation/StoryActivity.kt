package com.example.water_app.donation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.ActivityStoryBinding
import com.example.water_app.recyclerview.CommentAdapter
import com.example.water_app.repository.Instance
import com.example.water_app.repository.Repository
import com.example.water_app.user.MySharedPreferences
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoryActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityStoryBinding

    // 뷰 모델
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        // 뷰바인딩
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mlrd_ttl = this.intent.extras!!.getString("mlrd_ttl")
        val mlrd_cn = this.intent.extras!!.getString("mlrd_cn")

        binding.tvTitle.text = mlrd_ttl
        binding.tvContent.text = mlrd_cn

        // 댓글
        val mlrd_sn = this.intent.extras!!.getInt("mlrd_sn")
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getComment(mlrd_sn)
        viewModel.getCommentResponse.observe(this, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val commentList = it.body()

                //리사이클러뷰
                binding.rvComment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvComment.setHasFixedSize(true)
                binding.rvComment.adapter = CommentAdapter(this, commentList)
            }
        })

        // 댓글 작성
        binding.btnComment!!.setOnClickListener {
            getComment()

            // 새로고침
            finish() // 인텐트 종료
            overridePendingTransition(0, 0) // 인텐트 효과 없애기
            val intent = intent
            startActivity(intent)
            overridePendingTransition(0, 0) // 인텐트 효과 없애기
        }
    }

    private fun getComment() {
        val mbr_sn = MySharedPreferences.getUserSn(this).toInt()
        val mlrd_sn = this.intent.extras!!.getInt("mlrd_sn")
        val comt_cn = binding!!.edtComment.text.toString()

        val call = Instance.api.getComment(mbr_sn, mlrd_sn, comt_cn)
        call!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                //
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                //
            }
        })
    }
}