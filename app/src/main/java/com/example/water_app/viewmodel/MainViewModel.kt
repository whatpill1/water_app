package com.example.water_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_app.repository.Repository
import com.example.water_app.vo.CntrData
import com.example.water_app.vo.UserData
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository : Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<UserData>> = MutableLiveData()

    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser()
            myResponse.value = response
        }
    }

    val cntrResponse : MutableLiveData<Response<CntrData>> = MutableLiveData()

    fun getCntr() {
        viewModelScope.launch {
            val response = repository.getCntr()
            cntrResponse.value = response
        }
    }

}