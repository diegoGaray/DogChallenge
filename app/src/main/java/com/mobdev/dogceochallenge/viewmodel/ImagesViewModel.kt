package com.mobdev.dogceochallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobdev.dogceochallenge.model.Images
import com.mobdev.dogceochallenge.repository.ImageActivityRepository

class ImagesViewModel : ViewModel() {

    private var imagesLiveData: MutableLiveData<Images>? = null

    fun getImages(name: String) : LiveData<Images>? {
        imagesLiveData = ImageActivityRepository.getServicesApiCall(name)
        return imagesLiveData
    }

}