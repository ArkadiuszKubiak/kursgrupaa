package com.example.homework1.viewmodel.fragments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.homework1.models.PersonModel
import com.example.homework1.viewmodel.base.PersonBaseViewModel

class DetailsViewModel(application: Application) : PersonBaseViewModel<PersonModel>(
    application,
    MutableLiveData(), MutableLiveData()
) {
    fun init() {
        persons.value = loadPeople("item_details.json", arrayOf())
        selectedPersonId.value = persons.value!![0].id
    }
}