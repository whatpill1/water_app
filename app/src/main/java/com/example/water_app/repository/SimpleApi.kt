package com.example.water_app.repository

import com.example.water_app.vo.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {
    @GET("test2.php")
    suspend fun getUser(): Response<UserData>

    @GET("test3.php")
    suspend fun getCntr(): Response<PostData>

    @GET("test.php")
    suspend fun getHistory(@Query("use_yn") use_yn: Char): Response<List<HistoryData>>

    @POST("test7.php")
//    @Headers("Accept:application/json", "Content-Type:application/json",
//        "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c")
    fun createUser(@Body params: JoinData): Call<JoinResponse>
}