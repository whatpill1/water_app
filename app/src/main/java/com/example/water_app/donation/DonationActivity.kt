package com.example.water_app.donation

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.R
import com.example.water_app.databinding.ActivityDonationBinding
import com.example.water_app.main.MainActivity
import com.example.water_app.repository.Instance
import com.example.water_app.user.MySharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)

        // 뷰바인딩
        binding = ActivityDonationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트 getextra
        val cntr_sn = intent.extras!!.getInt("cntr_sn")
        val mbr_sn = MySharedPreferences.getUserSn(this).toInt()

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }
        binding.checkBox.setOnClickListener{
            if(binding.checkBox.isChecked){
                binding.btnDonation.isEnabled = true
                binding.btnDonation.setBackgroundResource(R.color.Blue)

                binding.btnDonation.setOnClickListener{
                    val btr_pc = binding.edtMoney.text.toString().toInt()
                    val btn_nm = binding.edtName.text.toString()
                    val call = Instance.api.postDonation(cntr_sn, mbr_sn, btn_nm, btr_pc)
                    call!!.enqueue(object : Callback<String?> {
                        override fun onResponse(call: Call<String?>, response: Response<String?>) {
                            val result = response.body()!!
                            if (result.toInt() == 0){
                                Toast.makeText(this@DonationActivity,"error", Toast.LENGTH_SHORT).show()
                            }else if(result.toInt() == 1){
                                Toast.makeText(this@DonationActivity,"error", Toast.LENGTH_SHORT).show()
                            }else if(result.toInt() == 2){
                                Toast.makeText(this@DonationActivity,"기부가 감사합니다.", Toast.LENGTH_SHORT).show()
                                startMain()
                            }else{
                                Toast.makeText(this@DonationActivity,"error", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<String?>, t: Throwable) {
                            Toast.makeText(this@DonationActivity,"error", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }else{
                binding.btnDonation.isEnabled = false
                binding.btnDonation.setBackgroundResource(R.color.grey)
            }
        }
    }

    private fun startMain(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}