package com.example.water_app.mypage

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water_app.R
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory

class MyPageActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvId = findViewById<TextView>(R.id.tvId)
        val tvNickName = findViewById<TextView>(R.id.tvNickName)
        val tvGen = findViewById<TextView>(R.id.tvGen)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvBirth = findViewById<TextView>(R.id.tvBirth)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful) {

//                Log.d("Response",it.body()?.mbr_id.toString())
//                Log.d("Response",it.body()?.mbr_ncnm.toString())
//                Log.d("Response",it.body()?.mbr_gen.toString())
//                Log.d("Response",it.body()?.mbr_tel.toString())
//                Log.d("Response",it.body()?.mbr_brthdy.toString())
//                Log.d("Response",it.body()?.mbr_email.toString())
                tvName.text = it.body()?.mbr_nm.toString()
                tvId.text = it.body()?.mbr_id.toString()
                tvNickName.text = it.body()?.mbr_ncnm.toString()
                tvGen.text = it.body()?.mbr_gen.toString()
                tvPhone.text = it.body()?.mbr_tel.toString()
                tvBirth.text = it.body()?.mbr_brthdy.toString()
                tvEmail.text = it.body()?.mbr_email.toString()
            }
            else{
                Log.d("Response",it.errorBody().toString())
            }
        })



    }
}