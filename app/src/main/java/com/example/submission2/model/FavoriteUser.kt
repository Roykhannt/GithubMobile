package com.example.submission2.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName ="favo_user")

data class FavoriteUser(
    val login:String?= null,
    @PrimaryKey
    val id: Int?= null,
    val avatar_url : String?=null
): Serializable