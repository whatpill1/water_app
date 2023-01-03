package com.example.water_app.join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water_app.R
import com.example.water_app.databinding.ActivityJoinBinding
import com.example.water_app.databinding.ActivityMyPageBinding
import com.example.water_app.databinding.ActivitySubmitBinding
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import com.example.water_app.vo.JoinData
import com.example.water_app.vo.JoinResponse

class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding
    private lateinit var viewModel : JoinActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }

        initViewModel()
        binding.btnJoin.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val join  = JoinData(binding.edtId.text.toString(), binding.edtPass.text.toString(), binding.edtName.text.toString(),binding.edtNick.text.toString(),binding.edtTel.text.toString(),binding.edtBirth.text.toString(),binding.edtEmail.text.toString())
        viewModel.createNewUser(join)

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(JoinActivityViewModel::class.java)
        viewModel.getCreateNewUserObserver().observe(this, Observer <JoinResponse?>{

            if(it  == null) {
                Toast.makeText(this@JoinActivity, "Failed to create User", Toast.LENGTH_LONG).show()
            } else {
                //{"code":201,"meta":null,"data":{"id":2877,"name":"xxxxxaaaaabbbbb","email":"xxxxxaaaaabbbbb@gmail.com","gender":"male","status":"active"}}
                Toast.makeText(this@JoinActivity, "Successfully created User", Toast.LENGTH_LONG).show()
            }
        })
    }
}