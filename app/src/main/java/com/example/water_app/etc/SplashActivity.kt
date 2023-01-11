package com.example.water_app.etc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.R
import com.example.water_app.main.MainActivity
import com.example.water_app.user.LoginActivity
import com.example.water_app.user.MySharedPreferences

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({
            val mbr_sn = MySharedPreferences.getUserSn(this)
            if (mbr_sn == " "){
                MySharedPreferences.setUserSn(this,"-1")
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else if (mbr_sn.toInt() == -1){
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }


        }, 1500)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}