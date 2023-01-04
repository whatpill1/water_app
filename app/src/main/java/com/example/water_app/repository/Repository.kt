package com.example.water_app.repository

import com.example.water_app.model.NoticeData
import com.example.water_app.model.PostData
import com.example.water_app.model.UserData
import retrofit2.Response

class Repository {
    // ViewModel에서 사용할 데이터 통신

    suspend fun getUser() : Response<UserData> {
        return RetrofitInstance.api.getUser()
    }

    suspend fun getCntr() : Response<PostData> {
        return RetrofitInstance.api.getCntr()
    }

    suspend fun getDonationList() : Response<List<PostData>> {
        return RetrofitInstance.api.getDonationList()
    }

    suspend fun getNoticeList() : Response<List<NoticeData>> {
        return RetrofitInstance.api.getNoticeList()
    }
}