package com.example.water_app.mypage

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water_app.R
import com.example.water_app.databinding.ActivityMyPageBinding
import com.example.water_app.main.MainActivity
import com.example.water_app.repository.Instance
import com.example.water_app.repository.Repository
import com.example.water_app.user.MySharedPreferences
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityMyPageBinding

    private lateinit var viewModel : MainViewModel


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        // 뷰바인딩
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        val mbr_sn = MySharedPreferences.getUserSn(this).toInt()

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getUser(mbr_sn)
        viewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful) {
                binding.tvName.text = it.body()?.mbr_sn.toString()
                binding.tvId.text = it.body()?.mbr_id.toString()
                binding.tvNickName.text = it.body()?.mbr_ncnm.toString()
                binding.tvGen.text = it.body()?.mbr_gen.toString()
                binding.tvPhone.text = it.body()?.mbr_tel.toString()
                binding.tvBirth.text = it.body()?.mbr_brthdy.toString()
                binding.tvEmail.text = it.body()?.mbr_email.toString()
            }
            else{
                Log.d("Response",it.errorBody().toString())
            }
        })

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }

        binding.btnDelete.setOnClickListener{
            val mDialogView =
                LayoutInflater.from(this).inflate(R.layout.mypage_delete, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val noButton = mDialogView.findViewById<Button>(R.id.btnNo)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
            val yesButton = mDialogView.findViewById<Button>(R.id.btnYes)
            yesButton.setOnClickListener {
                deleteUser()
            }
        }
    }
    fun deleteUser(){
        val mbr_sn = MySharedPreferences.getUserSn(this).toInt()
        val call = Instance.api.deleteUser(mbr_sn)
        call!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(this@MyPageActivity,"회원 탈퇴 완료.", Toast.LENGTH_SHORT).show()
                    reStart()
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
            }
        })
    }

    private fun reStart(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}