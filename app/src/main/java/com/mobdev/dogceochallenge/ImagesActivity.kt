package com.mobdev.dogceochallenge

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.mobdev.dogceochallenge.ImagesActivity
import com.mobdev.dogceochallenge.adapters.AdapterImage
import com.mobdev.dogceochallenge.api.Client
import com.mobdev.dogceochallenge.api.Service
import com.mobdev.dogceochallenge.modelo.Dogs
import com.mobdev.dogceochallenge.modelo.Images
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.*

class ImagesActivity  : AppCompatActivity() {

    private val listImages: MutableList<Images?> = ArrayList<Images?>()
    private var adapter: AdapterImage? = null
    private var mListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        receiveAndShowData()
    }

    fun receiveAndShowData() {
        val i = this.intent
        val name = i.extras!!.getString("NAME")
        val imgUrl = "https://dog.ceo/api/breed/" + name + "/images"
        val apiService =  Client.RetrofitClientInstance.retrofitInstance!!.create(Service::class.java)
        val call = apiService.getImages(imgUrl)
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                try {
                    val jsonArray = response.body()!!["message"].asJsonArray
                    println(jsonArray)
                    for (i in 0 until jsonArray.size()) {
                        val img = Images()
                        val name = jsonArray[i].asString
                        img.image = name
                        listImages.add(img)
                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }
                populateListView(listImages)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(this@ImagesActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun populateListView(images: List<Images?>) {
        mListView = findViewById(R.id.mListViewImages)
        adapter = AdapterImage(this, images as List<Images>)
        mListView!!.setAdapter(adapter)
    }

}