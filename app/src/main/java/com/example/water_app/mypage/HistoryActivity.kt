package com.example.water_app.mypage

import android.os.Bundle
import android.util.Log
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
    //private val historyAdapter by lazy { HistoryAdapter(this, historylist = null) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // 뷰바인딩
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val historylist = arrayListOf("1","2","3")
//        val historylist2 = arrayListOf("11","22","33")
//        val historylist3 = arrayListOf(historylist,historylist2)
////                val list : array<HistoryData> = historylist!!
//        Log.d("----------------1","$historylist")
//        Log.d("----------------2","$historylist2")
//        Log.d("----------------3","$historylist3")


        // 데이터 통신
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getHistory('Y')
        viewModel.historyResponse.observe(this, Observer {
            // 통신 성공
            if(it.isSuccessful){
                //var historylist: arrayListOf()
                val historylist = it.body()
//                val list : array<HistoryData> = historylist!!
                Log.d("----------------4","${historylist.toString()}")

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