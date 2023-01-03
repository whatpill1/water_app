package com.example.water_app.mypage

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.ActivityHistoryBinding
import com.example.water_app.recyclerview.HistoryAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory

class HistoryActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityHistoryBinding

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // 뷰바인딩
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 통신
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getDonationList()
        viewModel.getDonationListResponse.observe(this, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val historylist = it.body()
                //리사이클러뷰
                binding.rvHistory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvHistory.setHasFixedSize(true)  // 성능 개선
                binding.rvHistory.adapter = HistoryAdapter(this, historylist)
            }
            // 통신 실패
            else{
                Toast.makeText(this,it.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}