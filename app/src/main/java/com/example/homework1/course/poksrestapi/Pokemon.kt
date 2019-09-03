package com.example.homework1.course.poksrestapi

import com.example.homework1.course.Evolution
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon (
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("num")
    val num: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("img")
    val img: String,
    @Expose
    @SerializedName("type")
    val type: List<String>,
    @Expose
    @SerializedName("height")
    val height: String,
    @Expose
    @SerializedName("weight")
    val weight: String,
    @Expose
    @SerializedName("candy")
    val candy: String,
    @Expose
    @SerializedName("candy_count")
    val candy_count: Int,
    @Expose
    @SerializedName("egg")
    val egg: String,
    @Expose
    @SerializedName("spawn_chance")
    val spawn_chance: Double,
    @Expose
    @SerializedName("spawn_time")
    val spawn_time: String,
    @Expose
    @SerializedName("multipliers")
    val multipliers: List<String>,
    @Expose
    @SerializedName("weaknesses")
    val weaknesses: List<String>,
    @Expose
    @SerializedName("next_evolution")
    val next_evolution: List<Evolution>,
    @Expose
    @SerializedName("prev_evolution")
    val prev_evolution: List<Evolution>




)