package com.app.data.base.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entityToInsert: T)

    @Update
    fun update(entityToInsert: T): Int

    @Delete
    fun delete(items: List<T>): Int

    @Delete
    fun delete(item: T): Int
}