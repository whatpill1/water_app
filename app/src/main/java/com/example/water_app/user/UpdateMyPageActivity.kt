package com.example.water_app.user

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.water_app.R
import com.example.water_app.databinding.ActivityMyPageBinding
import com.example.water_app.databinding.ActivityUdateMyPageBinding
import com.example.water_app.main.MainActivity
import com.example.water_app.repository.Instance
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_join.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class UpdateMyPageActivity : AppCompatActivity() {
    // 뷰바인딩
    private lateinit var binding: ActivityUdateMyPageBinding

    private lateinit var viewModel : MainViewModel


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_udate_my_page)

        // 뷰바인딩
        binding = ActivityUdateMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get
        val mbr_sn = MySharedPreferences.getUserSn(this).toInt()
        val mbr_id = this.intent.extras!!.getString("mbr_id")
        val mbr_nm = this.intent.extras!!.getString("mbr_nm")
        val mbr_ncnm = this.intent.extras!!.getString("mbr_ncnm")
        val mbr_gen = this.intent.extras!!.getString("mbr_gen")
        val mbr_tel = this.intent.extras!!.getString("mbr_tel")
        val mbr_brthdy = this.intent.extras!!.getString("mbr_brthdy")
        val mbr_email = this.intent.extras!!.getString("mbr_email")

        binding.tvName.text = mbr_nm.toString()
        binding.tvId.text = mbr_id.toString()
        binding.edtNickName.setText(mbr_ncnm)
        binding.tvGen.text = mbr_gen.toString()
        binding.edtPhone.setText(mbr_tel)
        binding.tvBirth.text = mbr_brthdy.toString()
        binding.edtEmail.setText(mbr_email)

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }
        binding.btnChange.setOnClickListener{

            val mbr_ncnm: String? = binding.edtNickName.toString()
            val mbr_password: String? = binding.edtPass.toString()
            val mbr_tel: String? = binding.edtPhone.toString()
            val mbr_email: String? = binding.edtEmail.toString()
            val call = Instance.api.updateUser(mbr_sn,mbr_ncnm,mbr_password,mbr_tel,mbr_email)
            call!!.enqueue(object : Callback<String?> {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    if (response.isSuccessful && response.body() != null) {
                        val result = response.body()!!
                        if (result.toInt() == 0){
                            Toast.makeText(this@UpdateMyPageActivity,"비밀번호를 설정해 주세요.", Toast.LENGTH_SHORT).show()
                        }else if(result.toInt() == 1){
                            Toast.makeText(this@UpdateMyPageActivity,"error", Toast.LENGTH_SHORT).show()
                        }else if(result.toInt() == 2){
                            Toast.makeText(this@UpdateMyPageActivity,"회원 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show()
                            reStart()
                        }else{
                            Toast.makeText(this@UpdateMyPageActivity,"error", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
                override fun onFailure(call: Call<String?>, t: Throwable) {
                }
            })

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
                    Toast.makeText(this@UpdateMyPageActivity,"회원 탈퇴 완료.", Toast.LENGTH_SHORT).show()
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