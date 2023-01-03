package com.example.water_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_app.repository.Repository
import com.example.water_app.vo.HistoryData
import com.example.water_app.vo.HomeData
import com.example.water_app.vo.PostData
import com.example.water_app.vo.UserData
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository : Repository) : ViewModel() {
    // 데이터 처리

    val myResponse : MutableLiveData<Response<UserData>> = MutableLiveData()
    val cntrResponse : MutableLiveData<Response<PostData>> = MutableLiveData()
    val historyResponse : MutableLiveData<Response<List<HistoryData>>> = MutableLiveData()
    val homeResponse : MutableLiveData<Response<List<HomeData>>> = MutableLiveData()

    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser()
            myResponse.value = response
        }
    }
    fun getCntr() {
        viewModelScope.launch {
            val response = repository.getCntr()
            cntrResponse.value = response
        }
    }
    fun getHistory(use_yn: Char) {
        viewModelScope.launch {
            val response = repository.getHistory(use_yn)
            historyResponse.value = response
        }
    }
    fun getHomeList() {
        viewModelScope.launch {
            val response = repository.getHomeList()
            homeResponse.value = response
        }
    }
}