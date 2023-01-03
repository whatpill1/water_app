package com.example.water_app.user

import android.util.Log
import com.example.water_app.model.JoinData
import com.example.water_app.repository.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class RetrofitWork(private val userInfo: JoinData) {
    fun work() {
        val service = RetrofitInstance.api

        service.join(userInfo)
            .enqueue(object : retrofit2.Callback<JoinData> {
                override fun onResponse(
                    call: Call<JoinData>,
                    response: Response<JoinData>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        Log.d("회원가입 성공", "$result")
                    }
                }

                override fun onFailure(call: Call<JoinData>, t: Throwable) {
                    Log.d("회원가입 실패", t.message.toString())
                }
            })
    }
}