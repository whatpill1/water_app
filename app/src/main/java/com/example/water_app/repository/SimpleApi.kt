package com.example.water_app.repository

import com.example.water_app.vo.PostData
import com.example.water_app.vo.UserData
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("test2.php")
    suspend fun getUser() : Response<UserData>

    @GET("test3.php")
    suspend fun getCntr() : Response<PostData>
}