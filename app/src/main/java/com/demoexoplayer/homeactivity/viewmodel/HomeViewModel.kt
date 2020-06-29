package com.demoexoplayer.homeactivity.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demoexoplayer.homeactivity.model.ApiResponse
import com.demoexoplayer.homeactivity.model.AppRepository

class HomeViewModel : ViewModel(){


    var articleList = MutableLiveData<ApiResponse>()
    var isLoadMore:Boolean=true




    fun getData(url: String) {



        try {

            AppRepository.getInstance().getList(url){ isSuccess:Boolean, apiResponse:ApiResponse? ->

                if (isSuccess) {

                    Log.e("sucess",""+apiResponse)





                    if(isSuccess ){

                        articleList.postValue((apiResponse))

                        isLoadMore=true
                    }
                    else{
                        false
                    }



                }

                else if (!isSuccess){

                    Log.e("wrong",""+apiResponse)
                    isLoadMore=true
                }
            }
        }

        catch (e:Exception){

            e.printStackTrace()
        }

    }





}


