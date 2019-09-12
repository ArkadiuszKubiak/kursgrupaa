package com.example.homework1.course.database

import androidx.lifecycle.LiveData
import com.example.homework1.course.models.PokemonData
import com.example.homework1.course.rest_api.ApiInterface
import org.jetbrains.anko.doAsync
import retrofit2.Response

class PokemonRepository(private val appDatabase: AppDatabase, private val webService: ApiInterface) {

    companion object {
        const val DELETE_TIMEOUT_SECONDS: Long = 60 * 60

        lateinit var instance: PokemonRepository

        fun getInstance(appDatabase: AppDatabase, webService: ApiInterface): PokemonRepository {
            instance = PokemonRepository(appDatabase, webService)
            return instance
        }
    }

    fun getAllPokemons(): LiveData<List<PokemonRecord>> {
        refreshPokemonData()
        return appDatabase.pokemonDao().getAllPokemons()
    }

    fun createTrainer(login: String, name: String = "", surname: String = "", img: String = "") {
        val pokeTrainer = PokeDexRecord(login, name, surname)
        pokeTrainer.img = img
        appDatabase.pokedexDao().insertPokedex(pokeTrainer)
    }

    fun getTrainer(name: String): LiveData<PokeDexRecord> {
        // Doesn't need refreshing, since it's internal DB.
        return appDatabase.pokedexDao().getPokeDexByLogin(name)
    }

    fun getOwnedPokemons(name: String): LiveData<List<PokemonRecord>> {
        // Doesn't need refreshing, since it's internal DB.
        return appDatabase.ownedPokemonsDao().getPokemonsByTrainerLogin(name)
    }

    fun addPokemonToPokedex(pokeName: String, trainerName: String) {
        appDatabase.ownedPokemonsDao().insertOwnedPokemon(OwnedPokemonRecord(pokeName, trainerName))
    }

    fun removePokemonFromPokedex(pokeName: String, trainerName: String) {
        appDatabase.ownedPokemonsDao().deleteOwnedPokemon(OwnedPokemonRecord(pokeName, trainerName))
    }

    private fun refreshPokemonData() {
        doAsync {
            val oldPokes = appDatabase.pokemonDao().getPokemonsOlderThan(DELETE_TIMEOUT_SECONDS)
            for (poke in oldPokes) {
                val currentTimestamp = System.currentTimeMillis() / 1000
                val pokemonResponse = (webService.getPokemonData(poke.name).execute() as Response<PokemonData>)

                pokemonResponse.body()?.let { pokemonData ->
                    val pokemon = PokemonRecord(
                        num = pokemonData.order,
                        name = pokemonData.name,
                        pokemon_data = pokemonData
                    )

                    val newSynchData = SynchData(
                        poke_name = pokemonData.name,
                        timestamp_seconds = currentTimestamp
                    )

                    appDatabase.pokemonDao().insertSynchData(newSynchData)
                    appDatabase.pokemonDao().insertPokemon(pokemon)
                }
            }
        }


    }
}