package com.example.water_app.repository

import com.example.water_app.model.*
import retrofit2.Call
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

    suspend fun getLogin(userData: UserData) : Response<UserData?>? {
        return Instance.api.getLogin(userData)
    }

    suspend fun join(
        mbr_nm: String?,
        mbr_id: String?,
        mbr_password: String?,
        mbr_ncnm: String?,
        mbr_gen: Char,
        mbr_tel: String?,
        mbr_brthdy: String?,
        mbr_email: String?) : Call<UserData?> {
        return Instance.api.join(mbr_nm, mbr_id, mbr_password, mbr_ncnm, mbr_gen, mbr_tel, mbr_brthdy, mbr_email)
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
}