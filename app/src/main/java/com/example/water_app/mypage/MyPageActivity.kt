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
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Update
import com.example.water_app.R
import com.example.water_app.databinding.ActivityMyPageBinding
import com.example.water_app.donation.CommunicationActivity
import com.example.water_app.main.MainActivity
import com.example.water_app.repository.Instance
import com.example.water_app.repository.Repository
import com.example.water_app.user.MySharedPreferences
import com.example.water_app.user.UpdateMyPageActivity
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

                val mbr_nm = it.body()?.mbr_nm.toString()
                val mbr_id = it.body()?.mbr_id.toString()
                val mbr_ncnm = it.body()?.mbr_ncnm.toString()
                val mbr_gen = it.body()?.mbr_gen.toString()
                val mbr_tel = it.body()?.mbr_tel.toString()
                val mbr_brthdy = it.body()?.mbr_brthdy.toString()
                val mbr_email = it.body()?.mbr_email.toString()


                binding.tvName.text = mbr_nm.toString()
                binding.tvId.text = mbr_id.toString()
                binding.tvNickName.text = mbr_ncnm.toString()
                binding.tvGen.text = mbr_gen.toString()
                binding.tvPhone.text = mbr_tel.toString()
                binding.tvBirth.text = mbr_brthdy.toString()
                binding.tvEmail.text = mbr_email.toString()

                binding.btnChange.setOnClickListener{
                    val intent = Intent(this, UpdateMyPageActivity::class.java)
                    intent.putExtra("mbr_id",mbr_id)
                    intent.putExtra("mbr_nm",mbr_nm)
                    intent.putExtra("mbr_ncnm",mbr_ncnm)
                    intent.putExtra("mbr_gen",mbr_gen)
                    intent.putExtra("mbr_tel",mbr_tel)
                    intent.putExtra("mbr_brthdy",mbr_brthdy)
                    intent.putExtra("mbr_email",mbr_email)
                    startActivity(intent)

                }

            }
            else{
                Log.d("Response",it.errorBody().toString())
            }
        })

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }
    }
}