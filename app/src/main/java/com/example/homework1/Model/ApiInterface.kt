package com.example.homework1

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("pokedex.json")
    fun getPokemons(): Call<List<Pokemon>>

}