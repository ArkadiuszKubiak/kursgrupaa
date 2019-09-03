package com.example.homework1.course.database

import android.app.Application
import android.util.Log
import com.example.homework1.course.PokemonClass
import com.example.homework1.course.poksrestapi.ApiClient
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val call: Call<PokemonClass> = ApiClient.getClient.getPokemons()
        call.enqueue(object : Callback<PokemonClass> {
            override fun onResponse(call: Call<PokemonClass>?, response: Response<PokemonClass>?) {

                doAsync {
                    val database = AppDatabase.getInstance(context = this@RoomApplication)
                    if (database.pokemonDao().all.isEmpty()) {
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
                                poks.candy_count,
                                poks.egg,
                                poks.spawn_chance,
                                poks.spawn_time
                            )
                            Log.d("arek", "" + pokemon.name)
                            pokemons.add(pokemon)
                        }
                        database.pokemonDao().insertAll(pokemons = pokemons)
                    }
                }
            }
            override fun onFailure(call: Call<PokemonClass>?, t: Throwable?) {
            }
        })

    }

}