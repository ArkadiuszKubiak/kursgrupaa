package com.example.homework1.viewmodel.activities

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.homework1.viewmodel.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {

    val loaded: MutableLiveData<Boolean> = MutableLiveData()
    val chosenPseudoModelPersonId: MutableLiveData<Int> = MutableLiveData()

    fun init()
    {
        loaded.value = false
        chosenPseudoModelPersonId.value = 0
    }
}