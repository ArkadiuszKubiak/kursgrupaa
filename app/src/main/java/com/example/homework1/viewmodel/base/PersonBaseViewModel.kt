package com.example.homework1.viewmodel.base

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.homework1.readJsonFromAssets
import com.google.gson.Gson

abstract class PersonBaseViewModel<T>(
    application: Application,
    val persons: MutableLiveData<Array<T>>,
    val selectedPersonId: MutableLiveData<Int>
) : BaseViewModel(application) {

    protected fun loadPeople(json_data_file: String, personType: Array<T>): Array<T>? {
        // Just to simulate that the data is being downloaded.
        Thread.sleep(100)

        val jsonStr = readJsonFromAssets(getApplication<Application>().applicationContext.assets, json_data_file)
        val gson = Gson()
        return gson.fromJson(jsonStr, personType.javaClass)
    }

    fun getPersonById(person_id: Int): T? {
        if (persons.value?.size!! > person_id) {
            return persons.value?.get(person_id)
        }
        return null
    }
}