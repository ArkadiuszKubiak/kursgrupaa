package com.example.homework1.course.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
class PoksRecord constructor(
    @PrimaryKey var uid: Int, @ColumnInfo(name = "num") var num: String?, @ColumnInfo(name = "name") var name: String?, @ColumnInfo(
        name = "img"
    ) var img: String?, @ColumnInfo(name = "height") var height: String?,
    @ColumnInfo(name = "weight") var weight: String?, @ColumnInfo(name = "candy") var candy: String?, @ColumnInfo(
        name = "candy_count"
    ) var candy_count: Int, @ColumnInfo(name = "egg") var egg: String?, @ColumnInfo(name = "spawn_chance") var spawn_chance: Double,
    @ColumnInfo(name = "spawn_time") var spawn_time: String?
)