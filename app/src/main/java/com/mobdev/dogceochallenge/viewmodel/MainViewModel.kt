package com.mobdev.dogceochallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobdev.dogceochallenge.model.Dogs
import com.mobdev.dogceochallenge.repository.MainActivityRepository

class MainViewModel : ViewModel() {

    private var dogsLiveData: MutableLiveData<Dogs>? = null

    fun getDogs() : LiveData<Dogs>? {
        dogsLiveData = MainActivityRepository.getServicesApiCall()
        return dogsLiveData
    }

}