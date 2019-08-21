package com.example.homework1

import com.example.homework1.Model.PokemonClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("pokedex.json")
    fun getPokemons(): Call<PokemonClass>

}