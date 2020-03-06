package com.citrobyte.mpacker.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.citrobyte.mpacker.room.dao.UserDao
import com.citrobyte.mpacker.room.entities.User

@Database(entities = [(User::class)], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase(){
    abstract fun userDao(): UserDao
}