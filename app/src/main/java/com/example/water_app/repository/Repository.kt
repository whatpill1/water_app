package com.example.water_app.repository

import com.example.water_app.model.NoticeData
import com.example.water_app.model.PostData
import com.example.water_app.model.ReviewData
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
    suspend fun getLogin() : Response<UserData> {
        return Instance.api.getLogin()
    }
    suspend fun getCategory(cntr_category:Int?) : Response<List<PostData>> {
        return Instance.api.getCategory(cntr_category)
    }

    // 소통
    suspend fun getCommunication(cntr_sn:Int?) : Response<List<ReviewData>> {
        return Instance.api.getCommunication(cntr_sn)
    }
}