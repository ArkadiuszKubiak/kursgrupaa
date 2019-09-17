package com.example.homework1.course.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.homework1.course.database.PokeDexRecord
import com.example.homework1.course.database.PokemonRepository

class UserCreationViewModel(repository: PokemonRepository, application: Application) : ViewModelBase(repository, application) {
    companion object {
        val DEFAULT_POKE_TO_ADD = listOf("charmander", "bulbasaur", "pikachu", "squirtle")
    }

    fun createNewTrainer(login: String, name: String = "", surname: String = "", password: String = "", img: String = "") {
        repository.createTrainer(login, name, surname, password, img)
        // Each trainer's assigned a Pokemon!
        val randomPoke = DEFAULT_POKE_TO_ADD.random()
        repository.addPokemonToPokedex(randomPoke, login)
    }

    fun getTrainerByLogin(login: String) : LiveData<PokeDexRecord>
    {
        return repository.getTrainer(login)
    }

    fun removeTrainer(trainerData: PokeDexRecord)
    {
        repository.removeTrainer(trainerData)
    }
}