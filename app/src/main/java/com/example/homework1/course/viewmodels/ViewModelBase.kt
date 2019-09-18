package com.example.homework1.course.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.homework1.course.database.PokemonRepository

open class ViewModelBase(val repository: PokemonRepository, val application: Application) : ViewModel()