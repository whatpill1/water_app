package com.example.water_app.repository

import com.example.water_app.model.*
import com.example.water_app.model.PostData
import com.example.water_app.model.UserData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RestApi {
    // 마이페이지
    @GET("test2.php")
    suspend fun getUser(): Response<UserData>

    // 진행중인 기부
    @GET("home.php")
    suspend fun getDonationList(): Response<List<PostData>>

    // 공지사항
    @GET("notice.php")
    suspend fun getNoticeList(): Response<List<NoticeData>>

    // 완료된 기부
    @GET("homeend.php")
    suspend fun getHomeend(): Response<List<PostData>>

    // 카테고리별 목록
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

    // 회원가입
    @FormUrlEncoded
    @POST("join.php")
    fun getUserRegist(
        @Field("mbr_id") mbr_id: String?,
        @Field("mbr_password") mbr_password: String?,
        @Field("mbr_nm") mbr_nm: String?,
        @Field("mbr_ncnm") mbr_ncnm: String?,
        @Field("mbr_gen") mbr_gen: Char,
        @Field("mbr_tel") mbr_tel: String?,
        @Field("mbr_brthdy") mbr_brthdy: String?,
        @Field("mbr_email") mbr_email: String?
    ): Call<String?>?

    // 로그인
    @FormUrlEncoded
    @POST("login.php")
    fun getUserLogin(
        @Field("mbr_id") mbr_id: String?,
        @Field("mbr_password") mbr_password: String?
    ): Call<String?>?

    // 댓글
    @FormUrlEncoded
    @POST("getcomt.php")
    suspend fun getComment(@Field("mlrd_sn") mlrd_sn: Int?): Response<List<CommentData>>

    // 댓글 작성
    @FormUrlEncoded
    @POST("comt.php")
    fun getComment(
        @Field("mbr_sn") mbr_sn: Int?,
        @Field("mlrd_sn") mlrd_sn: Int?,
        @Field("comt_cn") comt_cn: String?
    ): Call<String?>?
}