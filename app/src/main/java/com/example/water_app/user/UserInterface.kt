package com.example.water_app.user

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserInterface {

    companion object {
        const val USER_URL = "http://10.1.4.121/"
    }

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

    @FormUrlEncoded
    @POST("login.php")
    fun getUserLogin(
        @Field("mbr_id") mbr_id: String?,
        @Field("mbr_password") mbr_password: String?
    ): Call<String?>?
}