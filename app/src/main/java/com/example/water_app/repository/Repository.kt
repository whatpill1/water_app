package com.example.water_app.repository

import com.example.water_app.vo.HistoryData
import com.example.water_app.vo.PostData
import com.example.water_app.vo.UserData
import retrofit2.Response

class Repository {

    suspend fun getUser() : Response<UserData> {
        return RetrofitInstance.api.getUser()
    }

    suspend fun getCntr() : Response<PostData> {
        return RetrofitInstance.api.getCntr()
    }

    suspend fun getHistory() : Response<HistoryData> {
        return RetrofitInstance.api.getHistory()
    }
}