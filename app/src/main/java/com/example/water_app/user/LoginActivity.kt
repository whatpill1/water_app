package com.example.water_app.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.R
import com.example.water_app.databinding.ActivityLoginBinding
import com.example.water_app.main.MainActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferenceHelper = PreferenceHelper(this)

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
//            loginUser()
            val intent = Intent(this, MainActivity::class.java)
            MySharedPreferences.setUserSn(this,"17")
            //intent.putExtra("mbr_sn", 17)

            startActivity(intent)
        }

        binding.btnGuest.setOnClickListener{
            MySharedPreferences.setUserSn(this,"-1")
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
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e(TAG, "에러 = " + t.message)
            }
        })
    }

    private fun parseLoginData(response: String?) {
        try {
            val jsonObject = JSONObject(response)
            if (jsonObject.getString("status") == "true") {
                saveInfo(response)
                Toast.makeText(this@LoginActivity, "Login Successfully!", Toast.LENGTH_SHORT).show()
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


    // 통신 성공
//            if(it.isSuccessful){
////                if (it.body()?.mbr_sn != null) {
////                    Log.d("------->","${it.body()}")
////                    MySharedPreferences.setUserId(this, it.body()?.mbr_id.toString())
////                    MySharedPreferences.setUserPass(this, it.body()?.mbr_password.toString())
////                    MySharedPreferences.setUserSn(this, it.body()?.mbr_sn.toString())
////                    val intent = Intent(this, MainActivity::class.java)
////                    startActivity(intent)
//                }else{
//                    Toast.makeText(this@LoginActivity, "아이디 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
//                }

}