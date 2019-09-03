package com.example.homework1.course

import com.example.homework1.course.poksrestapi.Pokemon
import com.google.gson.annotations.SerializedName



class PokemonClass {
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>? = null
}