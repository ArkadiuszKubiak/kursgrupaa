package com.example.homework1.course.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework1.course.database.PokeDexRecord
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.database.PokemonRepository

class PokeDexViewModel(repository: PokemonRepository, application: Application) : ViewModelBase(repository, application) {
    companion object {
        val DEFAULT_POKE_TO_ADD = listOf("charmander", "bulbasaur", "pikachu")
    }

    fun loadTrainerData(name: String) {
        trainerData = repository.getTrainer(name)
        trainerPokemons = repository.getOwnedPokemons(name)
        selectedPokemon = MutableLiveData()
    }

    lateinit var trainerData: LiveData<PokeDexRecord>
    lateinit var selectedPokemon: MutableLiveData<PokemonRecord>
    lateinit var trainerPokemons: LiveData<List<PokemonRecord>>
}