package com.example.water_app.mypage

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    val id : String,
    val name : String,
    val country : String
)