package com.example.water_app.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.ActivityRecyclerviewBinding
import com.example.water_app.recyclerview.PeriodAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory

class PeriodActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityRecyclerviewBinding

    //뷰모델 불러오기
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        // 뷰바인딩
        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //php 데이터 불러오기
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getDonationList()
        viewModel.getDonationListResponse.observe(this, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val periodList = it.body()
                //리사이클러뷰
                binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.setHasFixedSize(true)        // 성능 개선
                binding.recyclerView.adapter = PeriodAdapter(periodList)
            }
            // 통신 실패
            else{
                Toast.makeText(this,it.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}