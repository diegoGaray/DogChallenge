package com.mobdev.dogceochallenge.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.mobdev.dogceochallenge.api.Client
import com.mobdev.dogceochallenge.api.Service
import com.mobdev.dogceochallenge.model.Dogs
import com.mobdev.dogceochallenge.utils.SharedParameters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val dogs = MutableLiveData<Dogs>()

    fun getServicesApiCall(): MutableLiveData<Dogs> {
        val myApiService = Client.RetrofitClientInstance.retrofitInstance!!.create(Service::class.java)
        val call = myApiService.getDogs(SharedParameters.LIST_DOG)


        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                try {
                    val jsonArray = response.body()!!["message"].asJsonArray
                    println(jsonArray)
                    for (i in 0 until jsonArray.size()) {
                        val name = jsonArray[i].asString
                        dogs.value = Dogs(name)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println(t.message)
            }
        })
        return dogs
    }

}