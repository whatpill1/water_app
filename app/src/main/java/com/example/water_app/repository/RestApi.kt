package com.example.water_app.repository

import com.example.water_app.model.*
import com.example.water_app.model.PostData
import com.example.water_app.model.UserData
import retrofit2.Call
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

    @FormUrlEncoded
    @POST("login.php")
    suspend fun getLogin(
        @Body userData: UserData
    ): Response<UserData?>?

    @FormUrlEncoded
    @POST("join.php")
    fun join(
        @Field("mbr_nm") mbr_nm: String?,
        @Field("mbr_id") mbr_id: String?,
        @Field("mbr_password") mbr_password: String?,
        @Field("mbr_ncnm") mbr_ncnm: String?,
        @Field("mbr_gen") mbr_gen: Char,
        @Field("mbr_tel") mbr_tel: String?,
        @Field("mbr_brthdy") mbr_brthdy: String?,
        @Field("mbr_email") mbr_email: String?
    ): Call<UserData?>

    @FormUrlEncoded
    @POST("category.php")
    suspend fun getCategory(@Field("cntr_category") cntr_category: Int?): Response<List<PostData>>

    // 소통
    @FormUrlEncoded
    @POST("com.php")
    suspend fun getCommunication(@Field("cntr_sn") cntr_sn: Int?): Response<List<ReviewData>>

    // 모금 내역
    @FormUrlEncoded
    @POST("collecter.php")
    suspend fun getCollecter(@Field("cntr_sn") cntr_sn: Int?): Response<List<CollectData>>


}