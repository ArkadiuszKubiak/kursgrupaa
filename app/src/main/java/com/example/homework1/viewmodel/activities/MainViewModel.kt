package com.example.homework1.viewmodel.activities

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.homework1.pseudomodels.PseudoModelPerson
import com.example.homework1.viewmodel.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {

    val loaded: MutableLiveData<Boolean> = MutableLiveData()
    val chosenPseudoModelPerson: MutableLiveData<PseudoModelPerson> = MutableLiveData()
}