package com.example.water_app.mypage

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water_app.R

class MyPageActivity : AppCompatActivity() {



    private lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        val tvId = findViewById<TextView>(R.id.tvId)
        val tvNickName = findViewById<TextView>(R.id.tvNickName)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful) {

                Log.d("Response",it.body()?.country.toString())
                Log.d("Response",it.body()?.name.toString())
                tvId.text = it.body()?.country.toString()
                tvNickName.text = it.body()?.name.toString()
//                Log.d("Response",it.body()?.name.toString())
//                Log.d("Response",it.body()?.country!!)

//                Log.d("Response", it.id.toString())
//                Log.d("Response", it.name)
//                Log.d("Response", it.country)
            }
            else{
                Log.d("Response",it.errorBody().toString())
            }
        })



    }
}