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

    private val TAG = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    private var preferenceHelper: PreferenceHelper? = null

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferenceHelper = PreferenceHelper(this)

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
            loginUser()



            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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
                    Log.e("onSuccess", response.body()!!)
                    val jsonResponse = response.body()
                    parseLoginData(jsonResponse)
                    successLogin()
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e(TAG, "에러 = " + t.message)
            }
        })

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

    private fun parseLoginData(response: String?) {
        try {
            val jsonObject = JSONObject(response)
            if (jsonObject.getString("status") == "true") {
                saveInfo(response)
                Toast.makeText(this, "Login Successfully!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun saveInfo(response: String?) {
        preferenceHelper!!.putIsLogin(true)
        try {
            val jsonObject = JSONObject(response)
            if (jsonObject.getString("status") == "true") {
                val dataArray = jsonObject.getJSONArray("data")
                for (i in 0 until dataArray.length()) {
                    val dataobj = dataArray.getJSONObject(i)
                    preferenceHelper!!.putId(dataobj.getString("mbr_id"))
                    preferenceHelper!!.putPass(dataobj.getString("mbr_pass"))
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}