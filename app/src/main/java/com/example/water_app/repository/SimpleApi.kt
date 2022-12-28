package com.example.water_app.repository

import com.example.water_app.vo.HistoryData
import com.example.water_app.vo.PostData
import com.example.water_app.vo.SignUpCheckOkResponse
import com.example.water_app.vo.UserData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {
    @GET("test2.php")
    suspend fun getUser() : Response<UserData>

    @GET("test3.php")
    suspend fun getCntr() : Response<PostData>

    @GET("test.php")
    suspend fun getHistory() : Response<HistoryData>

    @POST("join.php")
    fun join(@Body params: HashMap<String, Any>): Call<UserData>

}