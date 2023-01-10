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
    val loginResponse : MutableLiveData<Response<UserData>> = MutableLiveData()
    val getCategoryResponse : MutableLiveData<Response<List<PostData>>> = MutableLiveData()

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
    fun getLogin() {
        viewModelScope.launch {
            val response = repository.getLogin()
            loginResponse.value = response
        }
    }
    fun getCategory(cntr_category:Int?) {
        viewModelScope.launch {
            val response = repository.getCategory(cntr_category)
            getCategoryResponse.value = response
        }
    }
}