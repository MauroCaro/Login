package com.app.data.base.local

import kotlinx.coroutines.flow.Flow

interface BaseLocalSource<Entity : IdentifyEntity> {

    fun getAll(): Flow<List<Entity>>

    fun getById(id: String): Flow<Entity?>

    fun loadById(id: String): Entity? {
        TODO("Override loadbyId in the local source")
    }

    fun createOrUpdate(items: List<Entity>): List<Entity>

    fun createOrUpdate(items: Entity): Entity

    fun deleteAll(): Int

    fun deleteById(id: String): Int {
        TODO("Override deleteById in the local source if required")
    }
}