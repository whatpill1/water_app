package com.example.water_app.repository

import com.example.water_app.vo.User
import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<User> {
        return RetrofitInstance.api.getPost()
    }
}