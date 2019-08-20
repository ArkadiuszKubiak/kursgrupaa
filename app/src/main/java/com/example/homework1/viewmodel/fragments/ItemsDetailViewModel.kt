package com.example.homework1.viewmodel.fragments

import android.app.Application
import android.media.Image
import androidx.lifecycle.MutableLiveData
import com.example.homework1.viewmodel.base.BaseViewModel

class ItemsDetailViewModel(application: Application) : BaseViewModel(application) {
    val id: MutableLiveData<Int> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val position: MutableLiveData<String> = MutableLiveData()
    val avatar: MutableLiveData<Image> = MutableLiveData()

    val currentProject: MutableLiveData<String> = MutableLiveData()
    val currentTopic: MutableLiveData<String> = MutableLiveData()
}