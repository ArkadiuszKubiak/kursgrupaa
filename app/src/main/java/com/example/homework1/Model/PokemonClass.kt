package com.example.homework1.Model

import com.example.homework1.Pokemon
import com.google.gson.annotations.SerializedName



class PokemonClass {
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>? = null
}