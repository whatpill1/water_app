package com.example.water_app.repository

import com.example.water_app.vo.UserData
import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<UserData> {
        return RetrofitInstance.api.getPost()
    }
}