package com.example.water_app.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water_app.R
import com.example.water_app.databinding.ActivityCommunicationBinding
import com.example.water_app.databinding.ActivitySubmitBinding
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory

class SubmitActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivitySubmitBinding

    private lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        // 뷰바인딩
        binding = ActivitySubmitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getCntr()
        viewModel.cntrResponse.observe(this, Observer {
            if(it.isSuccessful) {

                binding.donationTtl.text = it.body()?.cntr_ttl.toString()
                binding.donationCn.text = it.body()?.cntr_cn.toString()
            }
            else{
                Log.d("Response",it.errorBody().toString())
            }
        })


        binding.btnDonation.setOnClickListener{
            val intent = Intent(this, DonationActivity::class.java)
            startActivity(intent)
        }
    }
}