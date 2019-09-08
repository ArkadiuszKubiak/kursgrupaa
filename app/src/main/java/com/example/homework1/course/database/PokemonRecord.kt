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
    @PrimaryKey @ColumnInfo(name = "login") var login: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "surname") var surname: String?
) {
    @ColumnInfo(name = "img")
    var img: String? = ""
}

@Entity(
    tableName = "owned_pokemon", foreignKeys =
    arrayOf(
        ForeignKey(
            entity = PokemonRecord::class,
            parentColumns = arrayOf("num"),
            childColumns = arrayOf("pokemon_num"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PokeDexRecord::class,
            parentColumns = arrayOf("login"),
            childColumns = arrayOf("pokedex_login"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
class OwnedPokemonRecord constructor(
    @ColumnInfo(name = "pokedex_login") var pokedex_login: String,
    @ColumnInfo(name = "pokemon_num") var pokemon_num: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}


