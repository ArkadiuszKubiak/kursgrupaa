package com.example.homework1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel: ViewModel(){
    val pokName = MutableLiveData<String>()
    val pokImg = MutableLiveData<String>()

    fun setPokName(newTitle:String)
    {
        pokName.value = newTitle
    }

    fun setImage(newImage:String)
    {
        pokImg.value = newImage
    }
}