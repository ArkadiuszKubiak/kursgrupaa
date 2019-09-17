package com.example.homework1.course.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.homework1.course.models.PokemonData

@Entity(tableName = "pokemon_all")
class PokemonRecord(
    @ColumnInfo(name = "num") val num: Int,
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "pokemon_data") val pokemon_data: PokemonData
)


@Entity(tableName = "pokedex")
class PokeDexRecord constructor(
    @PrimaryKey @ColumnInfo(name = "login") var login: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "surname") var surname: String?,
    @ColumnInfo(name = "password") var password: String?
) {
    @ColumnInfo(name = "img")
    var img: String? = ""
}

@Entity(
    tableName = "synch_data",
    foreignKeys =
    [
        ForeignKey(
            entity = PokemonRecord::class,
            parentColumns = arrayOf("name"),
            childColumns = arrayOf("poke_name"),
            onDelete = ForeignKey.CASCADE
        )]
)
class SynchData constructor(
    @ColumnInfo(name = "timestamp_seconds") var timestamp_seconds: Long,
    @ColumnInfo(name = "poke_name") var poke_name: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid: Long = 0

}


@Entity(
    tableName = "owned_pokemon",
    foreignKeys =
    [
        ForeignKey(
            entity = PokemonRecord::class,
            parentColumns = arrayOf("name"),
            childColumns = arrayOf("poke_name"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PokeDexRecord::class,
            parentColumns = arrayOf("login"),
            childColumns = arrayOf("pokedex_login"),
            onDelete = ForeignKey.CASCADE
        )]
)
class OwnedPokemonRecord constructor(
    @ColumnInfo(name = "pokedex_login") var pokedex_login: String,
    @ColumnInfo(name = "poke_name") var poke_name: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0
}