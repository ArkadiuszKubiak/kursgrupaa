package com.example.homework1.course.login

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @get:Query("SELECT * FROM users")
    val all: List<LoginRecord>

    @Query("SELECT * FROM users WHERE uid IN (:userid)")
    fun loadAllByIds(userid: Array<Int>): List<LoginRecord>

    @Insert
    fun insertAll(users: List<LoginRecord>)

    @Delete
    fun delete(client: LoginRecord)
}