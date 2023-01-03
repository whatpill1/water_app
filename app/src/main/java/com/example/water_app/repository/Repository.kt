package com.example.water_app.repository

import com.example.water_app.vo.HistoryData
import com.example.water_app.vo.HomeData
import com.example.water_app.vo.PostData
import com.example.water_app.vo.UserData
import retrofit2.Response
import retrofit2.http.Query

class Repository {
    // ViewModel에서 사용할 데이터 통신

    suspend fun getUser() : Response<UserData> {
        return RetrofitInstance.api.getUser()
    }

    suspend fun getCntr() : Response<PostData> {
        return RetrofitInstance.api.getCntr()
    }

    suspend fun getHistory(use_yn: Char) : Response<List<HistoryData>> {
        return RetrofitInstance.api.getHistory(use_yn)
    }

    suspend fun getHomeList() : Response<List<HomeData>> {
        return RetrofitInstance.api.getHomeList()
    }
}