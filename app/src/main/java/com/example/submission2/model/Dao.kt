package com.example.submission2.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    suspend fun tambahFav(favoriteUser: FavoriteUser)

    @Query("SELECT * FROM favo_user")
    fun getFavorite(): LiveData<List<FavoriteUser>>

    @Query("SELECT count(*) FROM favo_user WHERE favo_user.id=:id")
    suspend fun cekUser(id: Int): Int

    @Query("DELETE FROM favo_user WHERE favo_user.id=:id")
    suspend fun hapusFav(id: Int):Int
}