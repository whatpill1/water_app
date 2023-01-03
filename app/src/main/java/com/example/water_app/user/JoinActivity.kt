package com.example.water_app.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.water_app.R
import com.example.water_app.databinding.ActivityJoinBinding

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

//        binding.btnJoin.setOnClickListener {
//            createUser()
//        }
//        initViewModel()
    }

//    private fun createUser() {
//        val join  = JoinData(binding.edtId.text.toString(), binding.edtPass.text.toString(), binding.edtName.text.toString(), binding.edtNick.text.toString(),
//                             binding.edtTel.text.toString(),binding.edtBirth.text.toString(),binding.edtEmail.text.toString())
//        viewModel.createNewUser(join)
//
//    }
//
//    private fun initViewModel() {
//        viewModel = ViewModelProvider(this).get(JoinActivityViewModel::class.java)
//        viewModel.getCreateNewUserObserver().observe(this, Observer <JoinResponse?>{
//            if(it  == null) {
//                Toast.makeText(this@JoinActivity, "회원가입 실패", Toast.LENGTH_LONG).show()
//                Log.d("----->","$it")
//            } else {
//                //{"code":201,"meta":null,"data":{"id":2877,"name":"xxxxxaaaaabbbbb","email":"xxxxxaaaaabbbbb@gmail.com","gender":"male","status":"active"}}
//                Toast.makeText(this@JoinActivity, "환영합니다~", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
}