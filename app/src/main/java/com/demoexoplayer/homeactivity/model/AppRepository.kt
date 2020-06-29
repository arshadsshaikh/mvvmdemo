package com.demoexoplayer.homeactivity.model

import com.demoexoplayer.homeactivity.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository {

    fun getList( url:String,onResult: (isSuccess: Boolean, response: ApiResponse?) -> Unit) {

        try {
            ApiClient.instance.getList(url)!!.enqueue(object :Callback<ApiResponse?> {

                override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {

                    onResult(false, null)

                }

                override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {

                    if (response.isSuccessful)
                        onResult(true, response.body())
                    else
                        onResult(false, null)
                }

            })

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }



    companion object {
        private var INSTANCE: AppRepository? = null
        fun getInstance() = INSTANCE
            ?: AppRepository().also {
                INSTANCE = it
            }
    }
}