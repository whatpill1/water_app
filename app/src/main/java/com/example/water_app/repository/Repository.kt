package com.example.water_app.repository

import com.example.water_app.model.NoticeData
import com.example.water_app.model.PostData
import com.example.water_app.model.UserData
import retrofit2.Response

class Repository {
    // ViewModel에서 사용할 데이터 통신

    suspend fun getUser() : Response<UserData> {
        return Instance.api.getUser()
    }

    suspend fun getDonationList() : Response<List<PostData>> {
        return Instance.api.getDonationList()
    }

    suspend fun getNoticeList() : Response<List<NoticeData>> {
        return Instance.api.getNoticeList()
    }

    suspend fun getHomeend() : Response<List<PostData>> {
        return Instance.api.getHomeend()
    }
    suspend fun getCategory0() : Response<List<PostData>> {
        return Instance.api.getCategory0()
    }
    suspend fun getCategory1() : Response<List<PostData>> {
        return Instance.api.getCategory1()
    }
    suspend fun getCategory2() : Response<List<PostData>> {
        return Instance.api.getCategory2()
    }
    suspend fun getCategory3() : Response<List<PostData>> {
        return Instance.api.getCategory3()
    }
    suspend fun getCategory4() : Response<List<PostData>> {
        return Instance.api.getCategory4()
    }
}