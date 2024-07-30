package com.app.data.common.user.local

import androidx.room.Dao
import androidx.room.Query
import com.app.data.base.database.BaseDao
import com.app.data.common.user.model.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM UserEntity")
    fun getAll(): Flow<List<UserEntity>>

    @Query("SELECT id FROM UserEntity")
    fun getAllId(): List<String>?

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getByIdFlow(id: String): Flow<UserEntity>

    @Query("DELETE FROM UserEntity")
    fun deleteAll(): Int

    @Query("DELETE FROM UserEntity where id = :id")
    fun delete(id: String): Int

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun loadById(id: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE email = :userName AND password = :password")
    fun checkLoginCredentials(userName: String, password: String): Flow<UserEntity?>

}