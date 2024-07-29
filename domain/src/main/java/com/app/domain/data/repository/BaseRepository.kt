package com.app.domain.data.repository

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    fun getById(id: String): Flow<T?>
    fun loadById(id: String): T?
    suspend fun createOrUpdate(items: List<T>)
    suspend fun createOrUpdate(item: T)
    fun deleteAll(): Int
    suspend fun refresh()
}