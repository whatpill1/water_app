package com.example.water_app.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.water_app.R
import com.example.water_app.databinding.ActivityJoinBinding
import com.example.water_app.main.MainActivity
import com.example.water_app.repository.Instance
import com.example.water_app.repository.RestApi
import com.example.water_app.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class JoinActivity : AppCompatActivity() {

    val TAG = "JoinActivity"
    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

        binding.btnJoin!!.setOnClickListener {
            registerMe()
        }
    }

    private fun registerMe() {
        val mbr_id = binding!!.edtId.text.toString()
        val mbr_password = binding!!.edtPass.text.toString()
        val mbr_nm = binding!!.edtName.text.toString()
        val mbr_ncnm = binding!!.edtNick.text.toString()
        val mbr_gen = 'Y'
        val mbr_tel = binding!!.edtTel.text.toString()
        val mbr_birth = binding!!.edtBirth.text.toString()
        val mbr_email = binding!!.edtEmail.text.toString()

        val retrofit = Retrofit.Builder()
            .baseUrl(Instance.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api = retrofit.create(RestApi::class.java)
        val call = api.getUserRegist(
            mbr_id, mbr_password, mbr_nm, mbr_ncnm, mbr_gen, mbr_tel, mbr_birth, mbr_email)
        call!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful && response.body() != null) {
                    val joinConfirm = response.body()!!
                    if (joinConfirm.toString() == "{\"mbr_sn\":0}"){
                        Toast.makeText(this@JoinActivity,"모든 칸을 채워주세요", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@JoinActivity,"회원가입 성공", Toast.LENGTH_SHORT).show()
                        startLoginPage()
                    }
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e(TAG, "에러 = " + t.message)
            }
        })
    }
    private fun startLoginPage(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}