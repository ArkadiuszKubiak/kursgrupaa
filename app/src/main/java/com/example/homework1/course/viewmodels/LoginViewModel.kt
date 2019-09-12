package com.example.homework1.course.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.homework1.course.database.PokemonRepository

class LoginViewModel(repository: PokemonRepository, application: Application) : ViewModelBase(repository, application) {
    val LoginName = MutableLiveData<String>()

}