package com.example.homework1.course.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemons")
    fun getAllPokemons(): List<PokemonRecord>

    @Query("SELECT * FROM pokemons WHERE num IN (:pokemonId)")
    fun loadAllPokemonByNum(pokemonId: Array<Int>): List<PokemonRecord>

    @Insert
    fun insertAllPokemons(pokemons: List<PokemonRecord>)

    @Delete
    fun deletePokemon(client: PokemonRecord)
}

@Dao
interface OwnedPokemonDao {
    @Query("SELECT * FROM owned_pokemon")
    fun getAllOwnedPokemons(): List<OwnedPokemonRecord>

    @Query("SELECT * FROM owned_pokemon WHERE uid IN (:trainerId)")
    fun getPokemonsByTrainerId(trainerId: Array<Int>): List<OwnedPokemonRecord>

    @Insert
    fun insertPokemon(ownedPokemonRecord: OwnedPokemonRecord)

    @Delete
    fun deletePokemon(ownedPokemonRecord: OwnedPokemonRecord)
}

@Dao
interface PokeDexDao {
    @Query("SELECT * FROM pokedex")
    fun getAllPokeDexes(): List<PokeDexRecord>

    @Query("SELECT * FROM pokedex WHERE uid IN (:trainerId)")
    fun getPokeDexById(trainerId: Array<Int>): List<PokeDexRecord>

    @Query("SELECT * FROM pokedex WHERE name == (:trainerName)")
    fun getPokeDexByName(trainerName: String): List<PokeDexRecord>

    @Query("SELECT * FROM pokedex WHERE surname == (:trainerSurname)")
    fun getPokeDexBySurname(trainerSurname: String): List<PokeDexRecord>

    @Insert
    fun insertPokedex(pokedex: PokeDexRecord)

    @Delete
    fun deletePokedex(pokedex: PokeDexRecord)
}



