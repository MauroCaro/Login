package com.app.data.base.database

import com.app.data.base.local.IdentifyEntity

abstract class UpdateStorageOperation<T : IdentifyEntity>(private val db: RoomDao? = null ) {

    abstract fun getAllIds(): List<String>?

    abstract fun createOrUpdate(item: T)

    abstract fun delete(id: String)

    fun execute(itemsToInsert: List<T>): List<T> {
        val newItemsId = ArrayList<String>()
        var oldItemsId: List<String>? = getAllIds()
        itemsToInsert.forEach { toInsert ->
            newItemsId.add(toInsert.id)
            createOrUpdate(toInsert)
        }
        oldItemsId?.forEach {
            if (!newItemsId.contains(it)) {
                delete(it)
            }
        }

        return itemsToInsert
    }

}