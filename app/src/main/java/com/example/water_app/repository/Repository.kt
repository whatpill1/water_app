package com.example.water_app.repository

import com.example.water_app.model.*
import retrofit2.Call
import retrofit2.Response

class Repository {
    // ViewModel에서 사용할 데이터 통신

    suspend fun getUser(mbr_sn: Int?) : Response<UserData> {
        return Instance.api.getUser(mbr_sn)
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

    suspend fun getCategory(cntr_category:Int?) : Response<List<PostData>> {
        return Instance.api.getCategory(cntr_category)
    }

    // 소통
    suspend fun getCommunication(cntr_sn:Int?) : Response<List<ReviewData>> {
        return Instance.api.getCommunication(cntr_sn)
    }

    // 모금내역
    suspend fun getCollecter(cntr_sn:Int?) : Response<List<CollectData>> {
        return Instance.api.getCollecter(cntr_sn)
    }

    // 댓글
    suspend fun getComment(mlrd_sn:Int?) : Response<List<CommentData>> {
        return Instance.api.getComment(mlrd_sn)
    }

    // 나의 후원 목록
    suspend fun getMy(mbr_sn:Int?) : Response<List<PostData>> {
        return Instance.api.getMy(mbr_sn)
    }
}