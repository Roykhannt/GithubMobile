package com.example.submission2.model

import com.google.gson.annotations.SerializedName

data class DataDetail(
    @SerializedName("id")
    val id : Int?= null,

    @SerializedName("login")
    val username : String?= null,

    @SerializedName("avatar_url")
    val avatar: String?= null,

    @SerializedName("name")
    val fullname: String?= null,

    @SerializedName("followers_url")
    val followers_url: String?= null,

    @SerializedName("following_url")
    val following_url: String?= null,

    @SerializedName("company")
    val company: String?= null,

    @SerializedName("location")
    val location: String?= null,

    @SerializedName("public_repos")
    val repository: String?= null,

    @SerializedName("followers")
    val followers: String?= null,

    @SerializedName("following")
    val following: String?= null


)
