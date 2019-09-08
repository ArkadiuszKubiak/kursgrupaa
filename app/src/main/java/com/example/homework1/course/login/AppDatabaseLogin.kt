package com.example.homework1.course.login

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(LoginRecord::class)], version = 5, exportSchema = false)
abstract class AppDatabaseLogin : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var sInstance: AppDatabaseLogin? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabaseLogin {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabaseLogin::class.java, "user")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
    }

}