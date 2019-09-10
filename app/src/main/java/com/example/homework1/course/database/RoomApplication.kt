package com.example.homework1.course.database

import android.app.Application
import android.util.Log
import com.example.homework1.course.models.PokemonAll
import com.example.homework1.course.models.PokemonData
import com.example.homework1.course.rest_api.ApiClient
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomApplication : Application() {

    companion object {
        const val DELETE_TIMEOUT_SECONDS = 60 * 60
        const val POKEMON_TO_DOWNLOAD = 151
        const val POKEMON_OFFSET = 0
    }

    override fun onCreate() {
        super.onCreate()
        val getAllPokemonsCall: Call<PokemonAll> = ApiClient.getClient.getPokemons(POKEMON_OFFSET, POKEMON_TO_DOWNLOAD)

        getAllPokemonsCall.enqueue(object : Callback<PokemonAll> {
            override fun onResponse(call: Call<PokemonAll>?, response: Response<PokemonAll>?) {
                doAsync {

                    val database = AppDatabase.getInstance(context = this@RoomApplication)

                    // Delete older items than the specified timeout.
                    val currentTimestamp = System.currentTimeMillis() / 1000
                    var xd = database.pokemonDao().getAllPokemons().size
                    database.pokemonDao().deleteOlderDataThan(currentTimestamp - DELETE_TIMEOUT_SECONDS)

                    // Because then it will download them.
                    if (database.pokemonDao().getAllPokemons().size < POKEMON_TO_DOWNLOAD) {
                        for (pokemonName in response!!.body()!!.results) {
                            val pokemonDataCall = ApiClient.getClient.getPokemonData(pokemonName.name)
                            val pokemonData = (pokemonDataCall.execute() as Response<PokemonData>).body()

                            val pokemon = PokemonRecord(
                                num = pokemonData!!.order,
                                name = pokemonName.name,
                                pokemon_data = pokemonData
                            )

                            val synchData = SynchData(
                                poke_num = pokemonData.order,
                                timestamp_seconds = currentTimestamp
                            )

                            Log.d("arek", "" + pokemon.name)

                            database.pokemonDao().insertPokemon(pokemon)
                            database.pokemonDao().insertSynchData(synchData)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PokemonAll>?, t: Throwable?) {
                Log.d("arek", "FAIL")
            }
        })
    }

}