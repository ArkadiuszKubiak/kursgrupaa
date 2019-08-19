package com.example.homework1.viewmodel.fragments

import android.media.Image
import androidx.lifecycle.MutableLiveData
import com.example.homework1.viewmodel.base.BaseViewModel



class ItemListViewModel : BaseViewModel() {

    val items: MutableLiveData<ItemsDetailViewModel> = MutableLiveData()

    private lateinit var neededFiels: List<String>


}