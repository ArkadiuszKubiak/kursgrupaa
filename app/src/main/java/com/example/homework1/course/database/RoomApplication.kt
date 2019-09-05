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
                    if (database.pokemonDao().getAll().isEmpty()) {
                        val pokemons: MutableList<PoksRecord> = mutableListOf()
                        for (poks in response!!.body()!!.pokemon!!) {
                            val pokemon = PoksRecord(
                                poks.id,
                                poks.num,
                                poks.name,
                                poks.img,
                                poks.height,
                                poks.weight,
                                poks.candy,
                                poks.candyCount,
                                poks.egg,
                                poks.spawnChance,
                                poks.spawnTime
                            )
                            Log.d("arek", "" + pokemon.name)
                            pokemons.add(pokemon)
                        }
                        database.pokemonDao().insertAll(pokemons = pokemons)
                    }
                }
            }

            override fun onFailure(call: Call<PokeDex>?, t: Throwable?) {
                Log.d("arek", "FAIL")
            }
        })

    }

}