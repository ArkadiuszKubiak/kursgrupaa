package com.example.homework1.course.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework1.course.database.OwnedPokemonRecord
import com.example.homework1.course.database.PokeDexRecord
import com.example.homework1.course.database.PokemonRepository

class PokeDexViewModel(val pokemonRepository: PokemonRepository) : ViewModel() {

    var currentTrainerData = MutableLiveData<PokeDexRecord>()
    var trainerOwnedPokemons = MutableLiveData<List<OwnedPokemonRecord>>()

    fun getTrainerByName(name: String): LiveData<PokeDexRecord> {
        currentTrainerData = pokemonRepository.getTrainer(name) as MutableLiveData<PokeDexRecord>
        return currentTrainerData
    }

    fun getOwnedPokemonsByTrainer(name: String): LiveData<List<OwnedPokemonRecord>> {
        trainerOwnedPokemons = pokemonRepository.getOwnedPokemons(name) as MutableLiveData<List<OwnedPokemonRecord>>
        return trainerOwnedPokemons
    }
}