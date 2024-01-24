package com.example.submission2.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.api.ApiClient
import com.example.submission2.model.DataUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class FollowViewModel: ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<DataUser>>()

    fun setListFollowers(uname: String){
        ApiClient.apiInstance
            .getFollowers(uname)
            .enqueue(object : Callback<ArrayList<DataUser>>{
                override fun onResponse(
                    call: Call<ArrayList<DataUser>>,
                    response: Response<ArrayList<DataUser>>
                ) {
                    if(response.isSuccessful){
                        listFollowers.postValue((response.body()))
                    }
                    else{
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }

                }

                override fun onFailure(call: Call<ArrayList<DataUser>>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                }

            })
    }
    fun getFollowers(): LiveData<ArrayList<DataUser>>{
        return listFollowers
    }

    val listFollowing = MutableLiveData<ArrayList<DataUser>>()

    fun setListFollowing(uname: String){
        ApiClient.apiInstance
            .getFollowing(uname)
            .enqueue(object : Callback<ArrayList<DataUser>>{
                override fun onResponse(
                    call: Call<ArrayList<DataUser>>,
                    response: Response<ArrayList<DataUser>>
                ) {
                    if(response.isSuccessful){
                        listFollowing.postValue((response.body()))
                    }
                    else{
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<ArrayList<DataUser>>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                }
            })
    }
    fun getFollowing(): LiveData<ArrayList<DataUser>>{
        return listFollowing
    }
}