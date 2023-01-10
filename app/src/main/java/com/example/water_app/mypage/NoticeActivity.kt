package com.example.water_app.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.ActivityRecyclerviewBinding
import com.example.water_app.recyclerview.NoticeAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_recyclerview.*

class NoticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerviewBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        // 뷰바인딩
        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getNoticeList()
        viewModel.noticeListResponse.observe(this, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val noticeList = it.body()
                // 리사이클러뷰
                binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.setHasFixedSize(true)  // 성능 개선
                binding.recyclerView.adapter = NoticeAdapter(this, noticeList)

                // 구분선
                val dividerItemDecoration =
                    DividerItemDecoration(recyclerView.context, LinearLayoutManager(this).orientation)

                recyclerView.addItemDecoration(dividerItemDecoration)

                // OnClickListener
                val adapter = NoticeAdapter(this, noticeList)

                adapter.setItemClickListener(object : NoticeAdapter.OnItemClickListener{
                    override fun onClick(v: View, position: Int) {
                    }
                })
                binding.recyclerView.adapter = adapter
            }
        })
    }
}