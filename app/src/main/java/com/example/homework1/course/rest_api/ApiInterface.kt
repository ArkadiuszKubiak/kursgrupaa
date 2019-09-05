package com.example.homework1.course.rest_api

import com.example.homework1.course.models.PokeDex
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("pokedex.json")
    fun getPokemons(): Call<PokeDex>

}