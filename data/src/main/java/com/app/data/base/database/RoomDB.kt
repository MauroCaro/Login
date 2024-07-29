package com.app.data.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.data.common.user.local.UserDao
import com.app.data.common.user.model.UserEntity

@Database(
    entities = [
        UserEntity::class
    ], version = 1, exportSchema = false
)
abstract class RoomDao : RoomDatabase() {

    abstract fun userDao(): UserDao
}