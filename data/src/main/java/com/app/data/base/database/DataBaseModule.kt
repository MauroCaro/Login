package com.app.data.base.database

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.app.data.common.user.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RoomDao {
        return Room.databaseBuilder(
            app,
            RoomDao::class.java,
            "RoomDB"
        ).fallbackToDestructiveMigration().build()
    }



    @Provides
    fun provideUserDao(database: RoomDao): UserDao {
        return database.userDao()
    }
}