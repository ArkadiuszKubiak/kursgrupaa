package com.example.homework1.viewmodel.fragments

import android.app.Application
import android.content.res.AssetManager
import com.example.homework1.adapters.MyAdapter
import com.example.homework1.viewmodel.fragments.ItemsDetailViewModel

import android.media.Image
import androidx.lifecycle.MutableLiveData
import com.example.homework1.pseudomodels.PseudoModelPerson
import com.example.homework1.viewmodel.base.BaseViewModel

import com.example.homework1.*
import com.example.homework1.pseudomodels.PseudoModelPersonList
import com.google.gson.Gson
import org.json.JSONArray
import java.io.StringReader

class ItemListViewModel(application: Application) : BaseViewModel(application) {

    lateinit var persons: MutableLiveData<Array<PseudoModelPersonList>>

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
}