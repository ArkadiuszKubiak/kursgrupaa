package com.example.homework1

import com.google.gson.annotations.SerializedName

class PokemonResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("categories")
    var pokemons: ArrayList<Pokemon>? = null

}