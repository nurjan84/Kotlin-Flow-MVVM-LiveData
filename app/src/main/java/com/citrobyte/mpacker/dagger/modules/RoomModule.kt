package com.citrobyte.mpacker.dagger.modules

import android.content.Context
import androidx.room.Room
import com.citrobyte.mpacker.dagger.scopes.AppScope
import com.citrobyte.mpacker.room.dao.UserDao
import com.citrobyte.mpacker.room.db.DataBase
import dagger.Module
import dagger.Provides


@Module
class RoomModule {

    @AppScope
    @Provides
    fun providesRoomDatabase(context: Context): DataBase {
        return Room.databaseBuilder(context.applicationContext,
            DataBase::class.java, "magnum_packer.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @AppScope
    @Provides
    fun providesUserDao(db: DataBase): UserDao {
        return db.userDao()
    }

}