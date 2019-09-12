package com.example.homework1.course.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework1.course.database.PokeDexRecord
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.database.PokemonRepository

class PokemonCatchingViewModel(repository: PokemonRepository, application: Application) : ViewModelBase(repository, application) {

    companion object {
        private val MIN_CHANCE_TO_CATCH: Int = 30
    }

    var currentChanceToCatchPokemon = MIN_CHANCE_TO_CATCH

    var currentTrainerData: MutableLiveData<PokeDexRecord> = MutableLiveData()
    var trainerPokemons: MutableLiveData<List<PokemonRecord>> = MutableLiveData()

    var currentWildPokemon: MutableLiveData<PokemonRecord> = MutableLiveData()
    var currentTrainerPokemon: MutableLiveData<PokemonRecord> = MutableLiveData()

    fun getTrainerData(name: String): MutableLiveData<PokeDexRecord> {
        currentTrainerData.value = repository.getTrainer(name).value
        return currentTrainerData
    }

    fun getCurrentTrainerPokemon(): LiveData<PokemonRecord> {
        return currentTrainerPokemon
    }

    fun setCurrentTrainerPokemon(poke: PokemonRecord) {
        currentTrainerPokemon.value = poke
    }

    fun getCurrentTrainerPokemonsData(name: String): MutableLiveData<List<PokemonRecord>> {
        trainerPokemons.value = repository.getOwnedPokemons(name).value
        return trainerPokemons
    }

    fun getRandomWildPokemon(): LiveData<PokemonRecord> {
        currentChanceToCatchPokemon = MIN_CHANCE_TO_CATCH
        currentWildPokemon.value = repository.getRandomPokemon().value
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