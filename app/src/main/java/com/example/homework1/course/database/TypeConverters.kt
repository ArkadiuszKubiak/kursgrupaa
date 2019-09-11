package com.example.homework1.course.database

import androidx.room.TypeConverter
import com.example.homework1.course.models.PokemonData
import com.google.gson.Gson


class PokemonDataConverter {
    private var gson = Gson()

    @TypeConverter
    fun stringToListOfObjects(data: String?): PokemonData? {
        if (data == null) {
            return null
        }

        return gson.fromJson(data, PokemonData::class.java)
    }

    @TypeConverter
    fun listOfObjectsToString(someObjects: PokemonData): String {
        return gson.toJson(someObjects)
    }
}
