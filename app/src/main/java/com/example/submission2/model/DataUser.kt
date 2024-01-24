package com.example.submission2.model

import com.google.gson.annotations.SerializedName

class DataUser (

    @SerializedName("id")
    val id : Int?= null,

    @SerializedName("login")
    val username : String?= null,

    @SerializedName("avatar_url")
    val avatar: String?= null


)