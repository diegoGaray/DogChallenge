package com.mobdev.dogceochallenge

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.mobdev.dogceochallenge.adapters.AdapterDog
import com.mobdev.dogceochallenge.api.Client
import com.mobdev.dogceochallenge.api.Service
import com.mobdev.dogceochallenge.modelo.Dogs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private val listDog: MutableList<Dogs?> = ArrayList<Dogs?>()
    private var adapter: AdapterDog? = null
    private var mListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myApiService = Client.RetrofitClientInstance.retrofitInstance!!.create(Service::class.java)
        val call = myApiService.dogs
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                try {
                    val jsonArray = response.body()!!["message"].asJsonArray
                    println(jsonArray)
                    for (i in 0 until jsonArray.size()) {
                        val dogs = Dogs()
                        val name = jsonArray[i].asString
                        dogs.raza = name
                        listDog.add(dogs)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                populateListView(listDog)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun populateListView(dogs: List<Dogs?>) {
        mListView = findViewById(R.id.mListView)
        adapter = AdapterDog(this, dogs as List<Dogs>)
        mListView!!.setAdapter(adapter)
    }
}

