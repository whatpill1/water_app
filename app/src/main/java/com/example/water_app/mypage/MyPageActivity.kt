package com.example.water_app.mypage

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water_app.R
import com.example.water_app.databinding.ActivityCommunicationBinding
import com.example.water_app.databinding.ActivityMyPageBinding
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory

class MyPageActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityMyPageBinding

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        // 뷰바인딩
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getUser()
        viewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful) {

                binding.tvName.text = it.body()?.mbr_nm.toString()
                binding.tvId.text = it.body()?.mbr_id.toString()
                binding.tvNickName.text = it.body()?.mbr_ncnm.toString()
                binding.tvGen.text = it.body()?.mbr_gen.toString()
                binding.tvPhone.text = it.body()?.mbr_tel.toString()
                binding.tvBirth.text = it.body()?.mbr_brthdy.toString()
                binding.tvEmail.text = it.body()?.mbr_email.toString()
            }
            else{
                Log.d("Response",it.errorBody().toString())
            }
        })



    }
}