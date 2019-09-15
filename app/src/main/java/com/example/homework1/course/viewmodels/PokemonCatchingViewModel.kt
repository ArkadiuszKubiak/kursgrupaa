package com.example.homework1.course.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.homework1.course.database.PokeDexRecord
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.database.PokemonRepository

class PokemonCatchingViewModel(repository: PokemonRepository, application: Application) : ViewModelBase(repository, application) {


    companion object {
        private val MIN_CHANCE_TO_CATCH: Int = 30
        private val CHANCE_TO_ESCAPE_DURING_ATTACK: Int = 25
    }

    fun initViewModel(trainerName: String) {
        loginTrainer = trainerName
        setTrainerData(trainerName)
        loadCurrentPokemonsTrainer()
        loadRandomWildPokemon()
    }

    var loginTrainer: String = "UNKNOWN"

    var currentChanceToCatchPokemon = MIN_CHANCE_TO_CATCH

    lateinit var currentTrainerData: LiveData<PokeDexRecord>

    lateinit var trainerPokemons: LiveData<List<PokemonRecord>>

    var currentWildPokemon: MediatorLiveData<PokemonRecord> = MediatorLiveData()

    var currentTrainerPokemon: PokemonRecord? = null

    fun setTrainerData(loginName: String) {
        currentTrainerData = repository.getTrainer(loginName)
    }

    fun getTrainerData(): LiveData<PokeDexRecord> {
        return currentTrainerData
    }

    fun getCurrentTrainerPokemonsData(): LiveData<List<PokemonRecord>> {
        return trainerPokemons
    }

    fun loadCurrentPokemonsTrainer() {
        trainerPokemons = repository.getOwnedPokemons(loginTrainer)
    }

    fun getPokemonByName(name: String): LiveData<PokemonRecord> {
        return repository.getPokemonByName(name)
    }


    fun getRandomWildPokemon(): LiveData<PokemonRecord> {
        loadRandomWildPokemon()
        return currentWildPokemon
    }

    fun loadRandomWildPokemon() {
        currentChanceToCatchPokemon = MIN_CHANCE_TO_CATCH
        currentWildPokemon.addSource(repository.getRandomPokemon()) { result ->
            result?.let { currentWildPokemon.value = it }
        }
    }

    fun tryToCatchPokemon(): Boolean {
        val randomChance = (0..100).random()
        val success = randomChance > currentChanceToCatchPokemon

        if (success) {
            repository.addPokemonToPokedex(currentWildPokemon.value!!.name, currentTrainerData.value!!.login)
        }

        return success
    }

    fun onAttack(): Boolean {
        val randomChance = (0..100).random()
        val success = randomChance > CHANCE_TO_ESCAPE_DURING_ATTACK
        if (success) {
            currentChanceToCatchPokemon += 5
        }

        return success
    }


}