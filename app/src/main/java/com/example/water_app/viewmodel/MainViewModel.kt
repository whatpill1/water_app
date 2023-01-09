package com.example.water_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_app.model.NoticeData
import com.example.water_app.repository.Repository
import com.example.water_app.model.PostData
import com.example.water_app.model.UserData
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository : Repository) : ViewModel() {
    // 데이터 처리

    val myResponse : MutableLiveData<Response<UserData>> = MutableLiveData()
    val getDonationListResponse : MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    val noticeListResponse : MutableLiveData<Response<List<NoticeData>>> = MutableLiveData()
    val getHomeEndListResponse : MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    val getCategory0Response : MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    val getCategory1Response : MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    val getCategory2Response : MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    val getCategory3Response : MutableLiveData<Response<List<PostData>>> = MutableLiveData()
    val getCategory4Response : MutableLiveData<Response<List<PostData>>> = MutableLiveData()

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
    fun getCategory0() {
        viewModelScope.launch {
            val response = repository.getCategory0()
            getCategory0Response.value = response
        }
    }
    fun getCategory1() {
        viewModelScope.launch {
            val response = repository.getCategory1()
            getCategory1Response.value = response
        }
    }
    fun getCategory2() {
        viewModelScope.launch {
            val response = repository.getCategory2()
            getCategory2Response.value = response
        }
    }
    fun getCategory3() {
        viewModelScope.launch {
            val response = repository.getCategory3()
            getCategory3Response.value = response
        }
    }
    fun getCategory4() {
        viewModelScope.launch {
            val response = repository.getCategory4()
            getCategory4Response.value = response
        }
    }

}