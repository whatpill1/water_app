package com.example.water_app.repository

import com.example.water_app.model.*
import com.example.water_app.model.PostData
import com.example.water_app.model.UserData
import retrofit2.Response
import retrofit2.http.*

interface RestApi {
    @GET("test2.php")
    suspend fun getUser(): Response<UserData>

    @GET("test.php")
    suspend fun getDonationList(): Response<List<PostData>>

    @GET("notice.php")
    suspend fun getNoticeList(): Response<List<NoticeData>>
}