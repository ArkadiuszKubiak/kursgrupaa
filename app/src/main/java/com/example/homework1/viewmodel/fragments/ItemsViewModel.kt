package com.example.homework1.viewmodel.fragments

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.homework1.R
import com.example.homework1.pseudomodels.PseudoModelPersonList
import com.example.homework1.viewmodel.base.PersonBaseViewModel

class ItemsViewModel(application: Application) : PersonBaseViewModel<PseudoModelPersonList>(
    application,
    MutableLiveData(), MutableLiveData()
) {

    lateinit var selectedItemsHistory: MutableLiveData<MutableMap<Int, Int>>

    fun init() {
        persons.value = loadPeople("items_list.json", arrayOf())
        selectedPersonId.value = persons.value!![0].id

        selectedItemsHistory = MutableLiveData()
        selectedItemsHistory.value = mutableMapOf()

        selectedItemsHistory.value!![selectedPersonId.value!!] = 1
    }

    fun onItemClick(view: View, person_id: Int) {
        view.setBackgroundColor(view.context.resources.getColor(R.color.activeField))
        val person = getPersonById(person_id)
        selectedPersonId.value = person!!.id

        // Write History somehow.
        if (selectedItemsHistory.value!!.containsKey(person.id)) {
            selectedItemsHistory.value!![person.id] = selectedItemsHistory.value!![person.id]!! + 1

        } else {
            selectedItemsHistory.value!![person.id] = 1
        }
    }
}