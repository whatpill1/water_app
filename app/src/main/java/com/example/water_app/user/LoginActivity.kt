package com.example.water_app.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.R
import com.example.water_app.databinding.ActivityLoginBinding
import com.example.water_app.main.MainActivity
import com.example.water_app.model.UserData
import com.example.water_app.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 뷰바인딩
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }

        binding.btnJoin.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{
            loginUser()

        }
    }


    private fun loginUser() {
        val mbr_id = binding.edtEmail!!.text.toString().trim { it <= ' ' }
        val mbr_password = binding.edtPassword!!.text.toString().trim { it <= ' ' }

        val retrofit = Retrofit.Builder()
            .baseUrl(UserInterface.USER_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val api = retrofit.create(UserInterface::class.java)
        val call = api.getUserLogin(mbr_id, mbr_password)
        call!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful && response.body() != null) {
                    val mbr_sn = response.body()!!
                    if (mbr_sn.toInt() < 1)
                    {
                        Toast.makeText(this@LoginActivity,"아이디 혹은 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                    }else if(mbr_sn.toInt() > 1){

                        MySharedPreferences.setUserSn(this@LoginActivity,mbr_sn)
                        startLogin()

                    }else{
                        Toast.makeText(this@LoginActivity,"error", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e(TAG, "에러 = " + t.message)
            }
        })
    }
    private fun startLogin(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}