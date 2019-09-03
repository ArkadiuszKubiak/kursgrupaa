package com.example.homework1.course.poksrestapi

import com.example.homework1.course.PokemonClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("pokedex.json")
    fun getPokemons(): Call<PokemonClass>

}