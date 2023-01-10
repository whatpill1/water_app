package com.example.water_app.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water_app.R
import com.example.water_app.databinding.ActivityLoginBinding
import com.example.water_app.main.MainActivity
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 뷰바인딩
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // SharedPreferences 안에 값이 저장되어 있지 않을 때 -> Login
//        if(MySharedPreferences.getUserId(this).isNullOrBlank()
//            || MySharedPreferences.getUserPass(this).isNullOrBlank()) {
//            loginUser()
//        }
//        else { // SharedPreferences 안에 값이 저장되어 있을 때 -> MainActivity로 이동
//            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }

        binding.btnJoin.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun successLogin(){
//
//        val repository = Repository()
//        val viewModelFactory = MainViewModelFactory(repository)
//
////        val mbr_sn = this.intent.extras?.getString("mbr_sn")
//
//        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
//        viewModel.getLogin()
//        viewModel.loginResponse.observe(this, Observer {
//            if(it.isSuccessful) {
//                MySharedPreferences.setUserId(this, it.body()?.mbr_id.toString())
//                MySharedPreferences.setUserPass(this, it.body()?.mbr_password.toString())
//                MySharedPreferences.setUserSn(this, it.body()?.mbr_sn.toString())
//                Log.d("test----------->1","${MySharedPreferences.getUserSn(this)}")
//            }
//            else{
//                Log.d("Response",it.errorBody().toString())
//            }
//        })
//
//        MySharedPreferences.setUserId(this, binding.edtEmail!!.text.toString())
//        MySharedPreferences.setUserPass(this, binding.edtPassword!!.text.toString())
    }

}