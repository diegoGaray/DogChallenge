package com.mobdev.dogceochallenge.ui

import android.content.Context
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mobdev.dogceochallenge.R
import com.mobdev.dogceochallenge.adapters.AdapterDog
import com.mobdev.dogceochallenge.model.Dogs
import com.mobdev.dogceochallenge.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private val listDog: MutableList<Dogs?> = ArrayList()
    private var adapter: AdapterDog? = null
    private var mListView: ListView? = null
    private lateinit var context: Context

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        listDog.clear()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getDogs()!!.observe(this, { dogs ->

            populateListView(dogs)
        })
    }

    private fun populateListView(dogs: Dogs) {
        mListView = findViewById(R.id.mListView)
        listDog.add(dogs)
        adapter = AdapterDog(this, listDog)
        mListView!!.adapter = adapter

    }
}

