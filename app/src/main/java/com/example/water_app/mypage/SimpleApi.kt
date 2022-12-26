package com.example.water_app.mypage

import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("test2.php")
    suspend fun getPost() : Response<Post>
}