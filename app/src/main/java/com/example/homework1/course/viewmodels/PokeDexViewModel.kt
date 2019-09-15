package com.example.homework1.course.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.homework1.course.database.PokeDexRecord
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.database.PokemonRepository

class PokeDexViewModel(repository: PokemonRepository, application: Application) : ViewModelBase(repository, application) {
    //var trainerOwnedPokemons = MutableLiveData<List<PokemonRecord>>()

    companion object
    {
        val DEFAULT_POKE_TO_ADD = listOf("charmander", "bulbasaur", "pikachu")
    }
    fun createNewTrainer(login: String, name: String = "", surname: String = "", img: String = "") {
        repository.createTrainer(login, name, surname, img)
        val randomPoke = DEFAULT_POKE_TO_ADD.random()
        repository.addPokemonToPokedex(randomPoke, login)
    }


    fun getTrainerByName(name: String): LiveData<PokeDexRecord> {
        //currentTrainerData = repository.getTrainer(name) as MutableLiveData<PokeDexRecord>
        //return currentTrainerData
        return repository.getTrainer(name)
    }

    fun getOwnedPokemonsByTrainer(name: String): LiveData<List<PokemonRecord>> {
        //trainerOwnedPokemons = repository.getOwnedPokemons(name) as MutableLiveData<List<PokemonRecord>>
        //return trainerOwnedPokemons
        return repository.getOwnedPokemons(name)
    }
}