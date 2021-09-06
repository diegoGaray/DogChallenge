package com.mobdev.dogceochallenge.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.mobdev.dogceochallenge.api.Client
import com.mobdev.dogceochallenge.api.Service
import com.mobdev.dogceochallenge.model.Images
import com.mobdev.dogceochallenge.utils.SharedParameters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ImageActivityRepository {

    private val images  = MutableLiveData<Images>()

    fun getServicesApiCall(name: String): MutableLiveData<Images> {

        val myApiService = Client.RetrofitClientInstance.retrofitInstance!!.create(Service::class.java)
        val call = myApiService.getImages(SharedParameters.IMAGES_BASE + name + "/images")

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                try {
                    val jsonArray = response.body()!!["message"].asJsonArray

                    for (i in 0 until jsonArray.size()) {
                        images.value = Images(jsonArray[i].asString)
                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println(t.message)
            }
        })
        return images
    }
}