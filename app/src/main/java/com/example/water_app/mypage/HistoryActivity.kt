package com.example.water_app.mypage

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.databinding.ActivityRecyclerviewBinding
import com.example.water_app.recyclerview.HistoryAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_recyclerview.*

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerviewBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        // 뷰바인딩
        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
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
                binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.setHasFixedSize(true)  // 성능 개선
                binding.recyclerView.adapter = HistoryAdapter(this, historylist)

                // 구분선
                val dividerItemDecoration =
                    DividerItemDecoration(recyclerView.context, LinearLayoutManager(this).orientation)

                recyclerView.addItemDecoration(dividerItemDecoration)
            }

            // 통신 실패
            else{
                Toast.makeText(this,it.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}