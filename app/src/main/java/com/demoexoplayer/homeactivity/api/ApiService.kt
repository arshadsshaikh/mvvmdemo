package com.demoexoplayer.homeactivity.api

import com.demoexoplayer.homeactivity.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET()
    fun getList(@Url url: String?): Call<ApiResponse?>?
}