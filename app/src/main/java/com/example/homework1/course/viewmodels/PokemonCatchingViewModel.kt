package com.example.homework1.course.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.homework1.course.database.PokeDexRecord
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.database.PokemonRepository

class PokemonCatchingViewModel(repository: PokemonRepository, application: Application) : ViewModelBase(repository, application) {


    companion object {
        private val MIN_CHANCE_TO_CATCH: Int = 30
    }

    var loginTrainer: String = "UNKNOWN"

    var currentChanceToCatchPokemon = MIN_CHANCE_TO_CATCH

    lateinit var currentTrainerData: LiveData<PokeDexRecord>

    lateinit var trainerPokemons: LiveData<List<PokemonRecord>>

    lateinit var currentWildPokemon: LiveData<PokemonRecord>
    lateinit var currentTrainerPokemon: LiveData<PokemonRecord>

    fun setAndLoadTrainerData(loginName: String) {
        loginTrainer = loginName
        currentTrainerData = repository.getTrainer(loginName)
    }

    fun getTrainerData(): LiveData<PokeDexRecord> {
        return currentTrainerData
    }

    fun getCurrentTrainerPokemonsData(): LiveData<List<PokemonRecord>> {
        return trainerPokemons
    }

    fun getPokemonByName(name: String): LiveData<PokemonRecord> {
        return repository.getPokemonByName(name)
    }

    fun loadCurrentPokemonsTrainer() {
        trainerPokemons = repository.getOwnedPokemons(loginTrainer)
    }

    fun getRandomWildPokemon(): LiveData<PokemonRecord> {
        currentChanceToCatchPokemon = MIN_CHANCE_TO_CATCH
        // currentWildPokemon.value = repository.getRandomPokemon().value
        return currentWildPokemon
    }

    fun tryToCatchPokemon() {
        val randomChance = (0..100).random()

        if (randomChance > currentChanceToCatchPokemon) {
            repository.addPokemonToPokedex(currentWildPokemon.value!!.name, currentTrainerData.value!!.name)
        }
    }

    fun onAttack() {
        currentChanceToCatchPokemon += 5
    }


}