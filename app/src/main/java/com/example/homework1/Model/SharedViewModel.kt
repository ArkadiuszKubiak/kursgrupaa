package com.example.homework1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel: ViewModel(){
    val pokName = MutableLiveData<String>()
    val pokImg = MutableLiveData<String>()
    var numClicks = ArrayList<Int>()
    var myIndex = MutableLiveData<Int>()
    var numItems = MutableLiveData<Int>()

    fun setPokName(newTitle:String)
    {
        pokName.value = newTitle
    }

    fun setImage(newImage:String)
    {
        pokImg.value = newImage
    }

    fun setNumItems(items:Int)
    {
        numItems.value = items
    }

    fun setClickArray()
    {
        if(numClicks.size == 0)
        {
            for(x in 0 until numItems.value!!){
                numClicks.add(0)
            }
        }
    }

    fun setIndex(index:Int)
    {
        myIndex.value = index
    }

    fun IncClick()
    {
        if(numClicks.size > 0) {
            numClicks[myIndex.value!!]++
        }
    }
}