package com.example.submission2.viewModel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission2.api.ApiClient
import com.example.submission2.model.Dao
import com.example.submission2.model.DataDetail
import com.example.submission2.model.FavoriteUser
import com.example.submission2.model.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {


    val user = MutableLiveData<DataDetail>()
    private var userDao: Dao?
    private var userDB: UserDB?
    init {
        userDB = UserDB.getDB(application)
        userDao = userDB?.favoriteUserDao()
    }
    fun setUserDetail(uname: String){
        ApiClient.apiInstance
            .getDetail(uname)
            .enqueue(object : Callback<DataDetail>{
                override fun onResponse(call: Call<DataDetail>, response: Response<DataDetail>) {
                    if(response.isSuccessful){
                        Log.d(TAG, response.toString())
                        user.postValue(response.body())
                    }
                    Log.d(TAG, response.toString())
                }
                override fun onFailure(call: Call<DataDetail>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getUserDetail(): LiveData<DataDetail>{
        return user
    }
    suspend fun checkUser(id: Int) = userDao?.cekUser(id)

    fun tambahFav(username: String, id: Int, avatar_url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavoriteUser(username, id, avatar_url)
            userDao?.tambahFav(user)
        }
    }

    fun hapusFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.hapusFav(id)
        }
    }
}