package com.example.water_app.donation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.ActivityCommunicationBinding
import com.example.water_app.databinding.ActivityStoryBinding
import com.example.water_app.recyclerview.CommentAdapter
import com.example.water_app.recyclerview.StoryAdapter
import com.example.water_app.repository.Instance
import com.example.water_app.repository.Repository
import com.example.water_app.user.MySharedPreferences
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_com_history.*
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
        }
    }

    private fun getComment() {
        val mbr_sn = MySharedPreferences.getUserSn(this).toInt()
        val mlrd_sn = this.intent.extras!!.getInt("mlrd_sn")
        val comt_cn = binding!!.edtComment.text.toString()

        val call = Instance.api.getComment(mbr_sn, mlrd_sn, comt_cn)
        call!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful && response.body() != null) {
                    val joinConfirm = response.body()!!
                    if (joinConfirm.toString() == "") {
                        Toast.makeText(this@StoryActivity, "댓글을 작성해 주세요", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@StoryActivity, "댓글이 작성되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                // 네
            }
        })

    }
}