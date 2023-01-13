package com.example.water_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_app.model.*
import com.example.water_app.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainViewModel(private val repository : Repository) : ViewModel() {
    // 데이터 처리

    val myResponse : MutableLiveData<Response<UserData>> = MutableLiveData()
    val getDonationListResponse : MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    val noticeListResponse : MutableLiveData<Response<List<NoticeData>>> = MutableLiveData()
    val getHomeEndListResponse : MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    val getCategoryResponse : MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    val getCommunicationResponse : MutableLiveData<Response<List<ReviewData>>> = MutableLiveData()
    val getCollecterResponse : MutableLiveData<Response<List<CollectData>>> = MutableLiveData()
    val getCommentResponse : MutableLiveData<Response<List<CommentData>>> = MutableLiveData()

    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser()
            myResponse.value = response
        }
    }
    fun getDonationList() {
        viewModelScope.launch {
            val response = repository.getDonationList()
            getDonationListResponse.value = response
        }
    }
    fun getNoticeList() {
        viewModelScope.launch {
            val response = repository.getNoticeList()
            noticeListResponse.value = response
        }
    }
    fun getHomeEnd() {
        viewModelScope.launch {
            val response = repository.getHomeend()
            getHomeEndListResponse.value = response
        }
    }

    fun getCategory(cntr_category:Int?) {
        viewModelScope.launch {
            val response = repository.getCategory(cntr_category)
            getCategoryResponse.value = response
        }
    }

    // 소통
    fun getCommunication(cntr_sn:Int?) {
        viewModelScope.launch {
            val response = repository.getCommunication(cntr_sn)
            getCommunicationResponse.value = response
        }
    }

    // 모금 내역
    fun getCollecter(cntr_sn:Int?) {
        viewModelScope.launch {
            val response = repository.getCollecter(cntr_sn)
            getCollecterResponse.value = response
        }
    }

    // 댓글
    fun getComment(mlrd_sn:Int?) {
        viewModelScope.launch {
            val response = repository.getComment(mlrd_sn)
            getCommentResponse.value = response
        }
    }
}