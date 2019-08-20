package com.example.homework1.viewmodel.fragments

import android.app.Application

import androidx.lifecycle.MutableLiveData
import com.example.homework1.viewmodel.base.BaseViewModel

import com.example.homework1.*
import com.example.homework1.pseudomodels.PseudoModelPersonList
import com.google.gson.Gson

class ItemsViewModel(application: Application) : BaseViewModel(application) {

    lateinit var persons: MutableLiveData<Array<PseudoModelPersonList>>
    lateinit var selectedPerson: MutableLiveData<PseudoModelPersonList>

    fun init() {
        persons = MutableLiveData<Array<PseudoModelPersonList>>()
        persons.value = arrayOf()
        populatePersons()
        selectedPerson = MutableLiveData()
        selectedPerson.value = getPersonAtPosition(0)
    }

    private fun populatePersons() {

        val jsonStr = readJsonFromAssets(getApplication<Application>().applicationContext.assets, "items_list.json")
        val gson = Gson()
        persons.value = gson.fromJson(jsonStr, Array<PseudoModelPersonList>::class.java)
    }

    fun getPersonAtPosition(position: Int): PseudoModelPersonList? {
        if (persons.value?.size!! > position) {
            return persons.value?.get(position)
        }
        return null
    }

    fun onItemClick(position: Int)
    {
        val person = getPersonAtPosition(position)
        selectedPerson.value = person
    }
}