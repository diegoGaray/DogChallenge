package com.mobdev.dogceochallenge.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Service {
    @GET
    fun getDogs(@Url url: String?): Call<JsonObject>

    @GET
    fun getImages(@Url url: String?): Call<JsonObject>
}