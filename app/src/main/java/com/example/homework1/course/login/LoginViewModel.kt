package com.example.homework1.course.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    val user_name = MutableLiveData<String>()

    fun setName(name:String)
    {
        user_name.value = name
    }
}