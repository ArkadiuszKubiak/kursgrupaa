package com.example.homework1.course.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
class PokemonRecord constructor(
    @PrimaryKey @ColumnInfo(name = "num") var num: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "img") var img: String,
    @ColumnInfo(name = "height") var height: String,
    @ColumnInfo(name = "weight") var weight: String,
    @ColumnInfo(name = "candy") var candy: String,
    @ColumnInfo(name = "candy_count") var candy_count: Int,
    @ColumnInfo(name = "egg") var egg: String,
    @ColumnInfo(name = "spawn_chance") var spawn_chance: Double,
    @ColumnInfo(name = "spawn_time") var spawn_time: String
)

@Entity(tableName = "pokedex")
class PokeDexRecord constructor(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "surname") var surname: String?,
    @ColumnInfo(name = "img") var img: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Entity(
    tableName = "owned_pokemon", foreignKeys =
    arrayOf(
        ForeignKey(
            entity = PokemonRecord::class,
            parentColumns = arrayOf("num"),
            childColumns = arrayOf("pokemon_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PokeDexRecord::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("pokedex_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
class OwnedPokemonRecord constructor(
    @ColumnInfo(name = "pokedex_id") var pokedex_id: Int,
    @ColumnInfo(name = "pokemon_id") var pokemon_id: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}


