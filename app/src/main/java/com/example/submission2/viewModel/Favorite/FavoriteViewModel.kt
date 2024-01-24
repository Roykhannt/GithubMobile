package com.example.submission2.viewModel.Favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.submission2.model.Dao
import com.example.submission2.model.FavoriteUser
import com.example.submission2.model.UserDB


class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: Dao?
    private var userDB: UserDB?

    init {
        userDB= UserDB.getDB(application)
        userDao=userDB?.favoriteUserDao()
    }

    fun getFavorite(): LiveData<List<FavoriteUser>>? {
        return userDao?.getFavorite()
    }

}