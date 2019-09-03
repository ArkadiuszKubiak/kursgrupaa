package com.example.homework1.course.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PokemonDao {

    @get:Query("SELECT * FROM pokemons")
    val all: List<PoksRecord>

    @Query("SELECT * FROM pokemons WHERE uid IN (:pokemonId)")
    fun loadAllByIds(pokemonId: Array<Int>): List<PoksRecord>

    @Insert
    fun insertAll(pokemons: List<PoksRecord>)

    @Delete
    fun delete(client: PoksRecord)
}