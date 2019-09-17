package com.example.homework1.course.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonAll(
    @Expose
    @SerializedName("results")
    val results: List<Result>
)

data class Result(
    @Expose
    @SerializedName("name")
    val name: String
)


data class PokemonData(
    @Expose
    @SerializedName("abilities")
    val abilities: List<Ability>,

    @Expose
    @SerializedName("forms")
    val forms: List<Form>,

    @Expose
    @SerializedName("height")
    val height: Int,

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("order")
    val order: Int,

    @Expose
    @SerializedName("sprites")
    val sprites: Sprites,

    @Expose
    @SerializedName("stats")
    val stats: List<Stat>,

    @Expose
    @SerializedName("types")
    val types: List<Type>,

    @Expose
    @SerializedName("weight")
    val weight: Int
)

data class Ability(
    @Expose
    @SerializedName("ability")
    val ability: AbilityX
)

data class AbilityX(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("url")
    val url: String
)

data class Form(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("url")
    val url: String
)

data class Sprites(
    @Expose
    @SerializedName("front_default")
    val frontDefault: String,

    @Expose
    @SerializedName("back_default")
    val backDefault: String,

    @Expose
    @SerializedName("front_shiny")
    val frontShiny: String
)

data class Stat(
    @Expose
    @SerializedName("base_stat")
    val baseStat: Int,
    @Expose
    @SerializedName("stat")
    val stat: StatX
)

data class StatX(
    @Expose
    @SerializedName("name")
    val name: String
)

data class Type(
    @Expose
    @SerializedName("type")
    val type: TypeX
)

data class TypeX(
    @Expose
    @SerializedName("name")
    val name: String
)

data class PokemonPokedex(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("pokemon_entries")
    val pokemonEntries: List<PokemonEntry>,
    @Expose
    @SerializedName("region")
    val region: Region
)

data class PokemonEntry(
    @Expose
    @SerializedName("entry_number")
    val entryNumber: Int,

    @Expose
    @SerializedName("pokemon_species")
    val pokemonSpecies: PokemonSpecies
)

data class PokemonSpecies(
    @Expose
    @SerializedName("name")
    val name: String
)

data class Region(
    @Expose
    @SerializedName("name")
    val name: String
)