package com.example.water_app.join

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.water_app.repository.Constants
import com.example.water_app.repository.SimpleApi
import com.example.water_app.vo.JoinData
import com.example.water_app.vo.JoinResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivityViewModel: ViewModel() {

    lateinit var createNewUserLiveData: MutableLiveData<JoinResponse?>

    init {
        createNewUserLiveData = MutableLiveData()
    }

    fun getCreateNewUserObserver(): MutableLiveData<JoinResponse?> {
        return createNewUserLiveData
    }

    fun createNewUser(join: JoinData) {
        val retroService  = Constants.getJoinConstants().create(SimpleApi::class.java)
        val call = retroService.createUser(join)
        call.enqueue(object: Callback<JoinResponse> {
            override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<JoinResponse>, response: Response<JoinResponse>) {
                if(response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }
        })
    }
}