package com.example.homework1.viewmodel.fragments

import android.app.Application
import android.media.Image
import androidx.lifecycle.MutableLiveData
import com.example.homework1.pseudomodels.PseudoModelPerson
import com.example.homework1.pseudomodels.PseudoModelPersonList
import com.example.homework1.readJsonFromAssets
import com.example.homework1.viewmodel.base.BaseViewModel
import com.google.gson.Gson

class DetailsViewModel(application: Application) : BaseViewModel(application) {

    lateinit var persons: MutableLiveData<Array<PseudoModelPerson>>

    fun init() {
        persons = MutableLiveData<Array<PseudoModelPerson>>()
        persons.value = arrayOf()
        populatePersons()
    }

    private fun populatePersons() {

        val jsonStr = readJsonFromAssets(getApplication<Application>().applicationContext.assets, "item_details.json")
        val gson = Gson()
        persons.value = gson.fromJson(jsonStr, Array<PseudoModelPerson>::class.java)
    }

    fun getPersonAtPosition(position: Int): PseudoModelPerson? {
        if (persons.value?.size!! > position) {
            return persons.value?.get(position)
        }
        return null
    }
}