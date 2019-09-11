package com.example.homework1.course.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_all")
    fun getAllPokemons(): LiveData<List<PokemonRecord>>

    @Query("SELECT * FROM pokemon_all")
    fun getAllPokemonsNormal(): List<PokemonRecord>

    @Query("SELECT * FROM pokemon_all WHERE num == :pokemonId")
    fun getPokemonByNum(pokemonId: Int): LiveData<PokemonRecord>

    @Query("SELECT * FROM pokemon_all WHERE name == :pokemonName")
    fun getPokemonByName(pokemonName: Int): PokemonRecord

    @Insert(onConflict = REPLACE)
    fun insertAllPokemons(pokemons: List<PokemonRecord>)

    @Insert(onConflict = REPLACE)
    fun insertPokemon(pokemon: PokemonRecord)

    @Delete
    fun deletePokemon(pokemon: PokemonRecord)

    @Query("DELETE FROM pokemon_all WHERE num in (SELECT poke_name FROM synch_data WHERE synch_data.timestamp_seconds < :oldTimestamp )")
    fun deleteOlderDataThan(oldTimestamp: Long)

    @Insert(onConflict = REPLACE)
    fun insertSynchData(syncData: SynchData)

    @Query("SELECT * FROM synch_data WHERE poke_name == :pokemonName")
    fun getSyncDataByPokemonId(pokemonName: String): SynchData
}

@Dao
interface OwnedPokemonDao {
    @Query("SELECT * FROM owned_pokemon")
    fun getAllOwnedPokemons(): List<OwnedPokemonRecord>

    @Query("SELECT * FROM owned_pokemon WHERE pokedex_login == :trainerLogin")
    fun getOwnedPokemonsByTrainerLogin(trainerLogin: String): LiveData<List<OwnedPokemonRecord>>

    @Query("SELECT * FROM owned_pokemon WHERE poke_name == :pokemonName")
    fun getOwnedPokemonsByPokemonName(pokemonName: String): List<OwnedPokemonRecord>

    @Insert(onConflict = REPLACE)
    fun insertOwnedPokemon(ownedPokemonRecord: OwnedPokemonRecord)

    @Delete
    fun deleteOwnedPokemon(ownedPokemonRecord: OwnedPokemonRecord)
}

@Dao
interface PokeDexDao {
    @Query("SELECT * FROM pokedex")
    fun getAllPokeDexes(): List<PokeDexRecord>

    @Query("SELECT * FROM pokedex WHERE login == :trainerLogin")
    fun getPokeDexByLogin(trainerLogin: String): LiveData<PokeDexRecord>

    @Query("SELECT * FROM pokedex WHERE name == :trainerName")
    fun getPokeDexByName(trainerName: String): List<PokeDexRecord>

    @Query("SELECT * FROM pokedex WHERE surname == :trainerSurname")
    fun getPokeDexBySurname(trainerSurname: String): List<PokeDexRecord>

    @Insert(onConflict = REPLACE)
    fun insertPokedex(pokedex: PokeDexRecord)

    @Delete
    fun deletePokedex(pokedex: PokeDexRecord)
}



