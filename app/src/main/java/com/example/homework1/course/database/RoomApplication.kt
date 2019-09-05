package com.example.homework1.course.database

import android.app.Application
import android.util.Log
import com.example.homework1.course.models.PokeDex
import com.example.homework1.course.rest_api.ApiClient
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val call: Call<PokeDex> = ApiClient.getClient.getPokemons()

        call.enqueue(object : Callback<PokeDex> {
            override fun onResponse(call: Call<PokeDex>?, response: Response<PokeDex>?) {
                doAsync {
                    val database = AppDatabase.getInstance(context = this@RoomApplication)
                    if (!database.pokemonDao().getAllPokemons().isEmpty()) {
                        val pokemons: MutableList<PokemonRecord> = mutableListOf()
                        for (poks in response!!.body()!!.pokemon!!) {
                            val pokemon = PokemonRecord(
                                num = poks.num,
                                name = poks.name,
                                img = poks.img,
                                height = poks.height,
                                weight = poks.weight,
                                candy = poks.candy,
                                candy_count = poks.candyCount,
                                egg = poks.egg,
                                spawn_chance = poks.spawnChance,
                                spawn_time = poks.spawnTime
                            )
                            Log.d("arek", "" + pokemon.name)
                            pokemons.add(pokemon)
                        }
                        database.pokemonDao().insertAllPokemons(pokemons = pokemons)
                    }
                }
            }

            override fun onFailure(call: Call<PokeDex>?, t: Throwable?) {
                Log.d("arek", "FAIL")
            }
        })

        //val database = AppDatabase.getInstance(context = this@RoomApplication)
        //if (database.pokedexDao().getAllPokeDexes().isEmpty()) {
        //                database.pokedexDao().insertPokedex(PokeDexRecord(name="", surname = "", img=""))
        //        }


    }

}