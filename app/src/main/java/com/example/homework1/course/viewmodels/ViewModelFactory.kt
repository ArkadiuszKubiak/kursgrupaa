package com.example.homework1.course.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework1.course.database.AppDatabase
import com.example.homework1.course.database.PokemonRepository
import com.example.homework1.course.rest_api.ApiClient


class MyViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    companion object {
        private lateinit var instance: MyViewModelFactory

        fun getInstance(application: Application): MyViewModelFactory {
            if (instance != null) {
                instance = MyViewModelFactory(application)
            }
            return instance
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getDeclaredConstructor(PokemonRepository::class.java, Application::class.java)
            .newInstance(PokemonRepository.getInstance(AppDatabase.getInstance(application), ApiClient.getClient), application)
    }


}