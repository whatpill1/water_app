package com.example.water_app.repository

import com.example.water_app.model.*
import com.example.water_app.model.PostData
import com.example.water_app.model.UserData
import retrofit2.Response
import retrofit2.http.*

interface RestApi {
    @GET("test2.php")
    suspend fun getUser(): Response<UserData>

    @GET("home.php")
    suspend fun getDonationList(): Response<List<PostData>>

    @GET("notice.php")
    suspend fun getNoticeList(): Response<List<NoticeData>>

    @GET("homeend.php")
    suspend fun getHomeend(): Response<List<PostData>>

    @GET("category0.php")
    suspend fun getCategory0(): Response<List<PostData>>

    @GET("category1.php")
    suspend fun getCategory1(): Response<List<PostData>>

    @GET("category2.php")
    suspend fun getCategory2(): Response<List<PostData>>

    @GET("category3.php")
    suspend fun getCategory3(): Response<List<PostData>>

    @GET("category4.php")
    suspend fun getCategory4(): Response<List<PostData>>
}