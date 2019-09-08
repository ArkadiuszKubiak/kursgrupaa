package com.example.homework1.course.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class LoginRecord constructor(
    @PrimaryKey var uid: Int, @ColumnInfo(name = "user_name") var user_name: String?
)