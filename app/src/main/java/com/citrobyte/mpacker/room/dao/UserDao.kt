package com.citrobyte.mpacker.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.citrobyte.mpacker.room.entities.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(o: User)

    @Query("SELECT * FROM user WHERE userId =0")
    fun getUser():User?

    @Query("SELECT * FROM user WHERE userId =0")
    fun getUserFlow():Flow<User?>

    @Query("DELETE FROM user")
    fun deleteAll()
}