package com.example.submission2.api

import com.example.submission2.model.DataDetail
import com.example.submission2.model.DataUser
import com.example.submission2.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getCariUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getListUser(
        @Query("q") query: String?
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetail(
        @Path("username") username: String
    ): Call<DataDetail>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<DataUser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<DataUser>>
}