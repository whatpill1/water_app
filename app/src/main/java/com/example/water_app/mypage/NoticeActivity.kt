package com.example.water_app.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.ActivityNoticeBinding
import com.example.water_app.home.SubmitActivity
import com.example.water_app.recyclerview.NoticeAdapter
import com.example.water_app.model.NoticeData
import com.example.water_app.recyclerview.HistoryAdapter
import com.example.water_app.recyclerview.HomeAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory

class NoticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticeBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        // 뷰바인딩
        binding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getNoticeList()
        viewModel.noticeListResponse.observe(this, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val noticeList = it.body()
                //리사이클러뷰
                binding.rvNotice.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvNotice.setHasFixedSize(true)  // 성능 개선
                binding.rvNotice.adapter = NoticeAdapter(this, noticeList)

                // OnClickListener
                val adapter = NoticeAdapter(this, noticeList)

                adapter.setItemClickListener(object : NoticeAdapter.OnItemClickListener{
                    override fun onClick(v: View, position: Int) {

                    }
                })

                binding.rvNotice.adapter = adapter
            }
            // 통신 실패
            else{
                Toast.makeText(this,it.code(), Toast.LENGTH_SHORT).show()
            }
        })


    }
}