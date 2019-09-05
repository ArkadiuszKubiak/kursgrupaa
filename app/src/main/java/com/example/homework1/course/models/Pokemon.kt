package com.example.homework1.course.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PokeDex(
    @Expose
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>
)

data class Pokemon(
    @Expose
    @SerializedName("avg_spawns")
    val avgSpawns: Int,
    @Expose
    @SerializedName("candy")
    val candy: String,
    @Expose
    @SerializedName("candy_count")
    val candyCount: Int,
    @Expose
    @SerializedName("egg")
    val egg: String,
    @Expose
    @SerializedName("height")
    val height: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("img")
    val img: String,
    @Expose
    @SerializedName("multipliers")
    val multipliers: List<Double>?,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("next_evolution")
    val nextEvolution: List<Evolution>,
    @Expose
    @SerializedName("num")
    val num: String,
    @Expose
    @SerializedName("prev_evolution")
    val prevEvolution: List<Evolution>,
    @Expose
    @SerializedName("spawn_chance")
    val spawnChance: Double,
    @Expose
    @SerializedName("spawn_time")
    val spawnTime: String,
    @Expose
    @SerializedName("type")
    val type: List<String>,
    @Expose
    @SerializedName("weaknesses")
    val weaknesses: List<String>,
    @Expose
    @SerializedName("weight")
    val weight: String
)

data class Evolution(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("num")
    val num: String
)
