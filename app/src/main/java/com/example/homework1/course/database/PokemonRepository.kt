package com.example.homework1.course.database

import androidx.lifecycle.LiveData
import com.example.homework1.course.models.PokemonData
import com.example.homework1.course.rest_api.ApiInterface
import org.jetbrains.anko.doAsync
import retrofit2.Response

class PokemonRepository(private val appDatabase: AppDatabase, private val webService: ApiInterface) {

    companion object {
        const val DELETE_TIMEOUT_SECONDS = 60 * 60

        lateinit var instance: PokemonRepository

        fun getInstance(appDatabase: AppDatabase, webService: ApiInterface): PokemonRepository {
            instance = PokemonRepository(appDatabase, webService)
            return instance
        }

    }

    fun getAllPokemons(): LiveData<List<PokemonRecord>> {
        return appDatabase.pokemonDao().getAllPokemons()
    }

    fun getTrainer(name: String): LiveData<PokeDexRecord> {
        return appDatabase.pokedexDao().getPokeDexByLogin(name)
    }

    fun getOwnedPokemons(name: String): LiveData<List<OwnedPokemonRecord>> {
        return appDatabase.ownedPokemonsDao().getOwnedPokemonsByTrainerLogin(name)
    }

    private fun refreshPokemonData(pokemonName: String) {
        doAsync {
            val synchData = appDatabase.pokemonDao().getSyncDataByPokemonId(pokemonName)
            val currentTimestamp = System.currentTimeMillis() / 1000
            if (currentTimestamp - synchData.timestamp_seconds > DELETE_TIMEOUT_SECONDS) {
                val pokemonData = (webService.getPokemonData(pokemonName).execute() as Response<PokemonData>).body()

                val pokemon = PokemonRecord(
                    num = pokemonData!!.order,
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