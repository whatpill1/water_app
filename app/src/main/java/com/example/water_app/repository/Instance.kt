package com.example.water_app.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Instance {

    val BASE_URL = "http://10.1.4.121"
    var gson = GsonBuilder().setLenient().create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : RestApi by lazy {
        retrofit.create(RestApi::class.java)
    }
}