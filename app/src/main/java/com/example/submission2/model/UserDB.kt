package com.example.submission2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteUser::class],
    version = 1
)
abstract class UserDB: RoomDatabase() {
    companion object{
        var INSTANCE : UserDB?= null

        fun getDB(context: Context): UserDB?{
            if (INSTANCE==null){
                synchronized(UserDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, UserDB::class.java,"user_db").build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun favoriteUserDao(): Dao
}