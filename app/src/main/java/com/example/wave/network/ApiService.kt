package com.example.wave.network

import com.example.wave.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("api/")
    suspend fun getUsers(@Query("page") query: Int, @Query("results") results: Int): Response<UserResponse>
}