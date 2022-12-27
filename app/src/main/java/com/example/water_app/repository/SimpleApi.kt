package com.example.water_app.repository

import com.example.water_app.vo.User
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("test2.php")
    suspend fun getPost() : Response<User>

    @GET("test3.php")
    suspend fun getPost2() : Response<User>
}