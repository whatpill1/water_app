package com.example.water_app.repository

import com.example.water_app.vo.HistoryData
import com.example.water_app.vo.PostData
import com.example.water_app.vo.UserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {
    @GET("test2.php")
    suspend fun getUser() : Response<UserData>

    @GET("test3.php")
    suspend fun getCntr() : Response<PostData>

    @GET("test.php")
    suspend fun getHistory() : Response<HistoryData>

    @GET("test.php/{use_yn}")
    suspend fun getHistory(@Path("use_yn") use_yn: Char): Response<HistoryData>
}