package com.mobdev.dogceochallenge.ui

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mobdev.dogceochallenge.R
import com.mobdev.dogceochallenge.adapters.AdapterImage
import com.mobdev.dogceochallenge.model.Images
import com.mobdev.dogceochallenge.viewmodel.ImagesViewModel

class ImagesActivity : AppCompatActivity() {

    private val listImages: MutableList<Images?> = ArrayList()
    private var adapter: AdapterImage? = null
    private var mListView: ListView? = null
    private lateinit var context: Context

    private lateinit var imagesViewModel: ImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        val name = this.intent.extras!!.getString("NAME")
        context = this@ImagesActivity
        listImages.clear()
        imagesViewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)
        imagesViewModel.getImages(name!!)!!.observe(this, { images ->

            populateListView(images)
        })

    }


    private fun populateListView(images: Images) {
        mListView = findViewById(R.id.mListViewImages)
        listImages.add(images)
        adapter = AdapterImage(this, listImages)
        mListView!!.adapter = adapter
    }

}