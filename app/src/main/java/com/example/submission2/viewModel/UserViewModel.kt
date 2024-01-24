package com.example.submission2.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.api.ApiClient
import com.example.submission2.model.DataUser
import com.example.submission2.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel: ViewModel() {
    val listUser = MutableLiveData<ArrayList<DataUser>>()

    fun setCariUsers(query:String){
        ApiClient.apiInstance
            .getCariUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                }
            })
    }
    fun getCariUsers(): LiveData<ArrayList<DataUser>>{
        return listUser
    }
}