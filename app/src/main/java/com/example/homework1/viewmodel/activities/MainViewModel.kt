package com.example.homework1.viewmodel.activities

import androidx.lifecycle.MutableLiveData
import com.example.homework1.pseudomodels.PseudoModelPerson
import com.example.homework1.viewmodel.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    val loaded: MutableLiveData<Int> = MutableLiveData()
    val chosenPseudoModelPerson: MutableLiveData<PseudoModelPerson> = MutableLiveData()



}