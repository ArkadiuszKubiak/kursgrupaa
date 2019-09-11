package com.example.homework1.course.rest_api

import com.example.homework1.course.models.PokemonAll
import com.example.homework1.course.models.PokemonData
import com.example.homework1.course.models.PokemonPokedex
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2/pokemon")
    fun getPokemons(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonAll>

    @GET("v2/pokemon/{name}")
    fun getPokemonData(@Path("name") name: String): Call<PokemonData>


    @GET("v2/pokedex/{region}")
    fun getPokedex(@Path("region") region: String): Call<PokemonPokedex>
}

