package com.app.data.base.mapper

interface Mapper<Entity, Pojo> {

    fun mapRemote(remotePojo: Pojo): Entity

    fun mapLocal(entity: Entity): Pojo {
        return TODO() // implement when required
    }
}