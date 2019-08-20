package com.example.homework1

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon (
    @Expose
    @SerializedName("id")
    val id: Integer,
    @Expose
    @SerializedName("num")
    val num: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("img")
    val img: String
)