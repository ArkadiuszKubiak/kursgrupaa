package com.example.homework1.course.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework1.course.database.OwnedPokemonRecord
import com.example.homework1.course.database.PokeDexRecord
import com.example.homework1.course.database.PokemonRecord

class PokeDexViewModel : ViewModel() {
    val trainerName = MutableLiveData<String>()
    val trainerSurname = MutableLiveData<String>()
    val trainerLogin = MutableLiveData<String>()

    val trainerOwnedPokemons = MutableLiveData<List<OwnedPokemonRecord>>()
    val allPokemonDataSet = MutableLiveData<List<PokemonRecord>>()

    var currentTrainer: PokeDexRecord
        get() = PokeDexRecord(trainerName.value!!, trainerSurname.value!!, trainerLogin.value!!)
        set(trainer) {
            trainerName.value = trainer.name
            trainerSurname.value = trainer.surname
            trainerLogin.value = trainer.login
        }

    var ownedPokemons: List<OwnedPokemonRecord>
        get() = trainerOwnedPokemons.value!!
        set(pokemonData) {
            trainerOwnedPokemons.value = pokemonData
        }

    var pokemonDataSet: List<PokemonRecord>
        get() = allPokemonDataSet.value!!
        set(pokemonData) {
            allPokemonDataSet.value = pokemonData
        }

}