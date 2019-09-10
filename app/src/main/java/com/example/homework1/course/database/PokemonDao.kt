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

    @Query("SELECT * FROM pokemons WHERE num == :pokemonId")
    fun getPokemonByNum(pokemonId: Int): PokemonRecord

    @Insert
    fun insertAllPokemons(pokemons: List<PokemonRecord>)

    @Delete
    fun deletePokemon(client: PokemonRecord)

    @Query("DELETE FROM pokemons WHERE num in (SELECT poke_id FROM synch_data WHERE synch_data.timestamp_seconds > :oldTimestamp )")
    fun deleteOlderDataThan(oldTimestamp: Long)

    @Insert
    fun insertSynchData(syncData: SynchData)
}

@Dao
interface OwnedPokemonDao {
    @Query("SELECT * FROM owned_pokemon")
    fun getAllOwnedPokemons(): List<OwnedPokemonRecord>

    @Query("SELECT * FROM owned_pokemon WHERE pokedex_login == :trainerLogin")
    fun getOwnedPokemonsByTrainerLogin(trainerLogin: String): List<OwnedPokemonRecord>

    @Query("SELECT * FROM owned_pokemon WHERE pokemon_num == :pokemonNum")
    fun getOwnedPokemonsByPokemonNum(pokemonNum: String): List<OwnedPokemonRecord>

    @Insert
    fun insertOwnedPokemon(ownedPokemonRecord: OwnedPokemonRecord)

    @Delete
    fun deleteOwnedPokemon(ownedPokemonRecord: OwnedPokemonRecord)
}

@Dao
interface PokeDexDao {
    @Query("SELECT * FROM pokedex")
    fun getAllPokeDexes(): List<PokeDexRecord>

    @Query("SELECT * FROM pokedex WHERE login == :trainerLogin")
    fun getPokeDexByLogin(trainerLogin: String): PokeDexRecord

    @Query("SELECT * FROM pokedex WHERE name == :trainerName")
    fun getPokeDexByName(trainerName: String): List<PokeDexRecord>

    @Query("SELECT * FROM pokedex WHERE surname == :trainerSurname")
    fun getPokeDexBySurname(trainerSurname: String): List<PokeDexRecord>

    @Insert
    fun insertPokedex(pokedex: PokeDexRecord)

    @Delete
    fun deletePokedex(pokedex: PokeDexRecord)
}



