package com.app.data.base.repository

import com.app.data.base.local.BaseLocalSource
import com.app.data.base.local.IdentifyEntity
import com.app.data.base.mapper.Mapper
import com.app.domain.data.repository.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit

abstract class BaseRepositoryImpl<DomainModel, Entity : IdentifyEntity, RemotePojo>(
    private val localSource: BaseLocalSource<Entity>,
    private val dataMapper: Mapper<Entity, RemotePojo>,
    private val domainMapper: Mapper<DomainModel, Entity>,
) : BaseRepository<DomainModel> {

    open var rateLimiter = RateLimiter<String>(1, TimeUnit.MINUTES)

    open val operationGetAll =
        object : RepositoryOperation<List<Entity>, List<RemotePojo>> {

            override fun shouldFetch(result: List<Entity>?): Boolean {
                return shouldFetchAll(result)
            }

            override fun subscribeDbUpdates(): Flow<List<Entity>> {
                return getLocalData()
            }

            override suspend fun createCall(): List<RemotePojo> {
                return fetchAll()
            }

            override fun mapCallResult(result: List<RemotePojo>): List<Entity> {
                return result.map { mapResult(it) }
            }

            override suspend fun saveResult(result: List<Entity>) {
                saveDataLocally(result)
            }

        }

    fun getAll(): Flow<List<DomainModel>> = operationGetAll.asFlow().map {
        it.map {
            domainMapper.mapRemote(it)

        }
    }

    open fun shouldFetchAll(result: List<Entity>?): Boolean {
        return result.isNullOrEmpty() || rateLimiter.shouldFetch("operationGetAllTimeReportIds" + hashCode())
    }

    open fun saveDataLocally(result: List<Entity>) {
        localSource.createOrUpdate(result)
    }

    open suspend fun fetchAll(): List<RemotePojo> = listOf()

    open fun mapResult(data: RemotePojo) = dataMapper.mapRemote(data)

    open fun getLocalData(): Flow<List<Entity>> = localSource.getAll()

    override suspend fun createOrUpdate(items: List<DomainModel>) {
        items.map { domainMapper.mapLocal(it) }.apply {
            localSource.createOrUpdate(this)
        }
    }

    override suspend fun createOrUpdate(item: DomainModel) {
        localSource.createOrUpdate(domainMapper.mapLocal(item))
    }

    override fun deleteAll(): Int {
        return localSource.deleteAll()
    }

    override fun getById(id: String): Flow<DomainModel?> {
        return localSource.getById(id).map { it?.let { it1 -> domainMapper.mapRemote(it1) } }
    }

    override fun loadById(id: String): DomainModel? {
        val local = localSource.loadById(id)
        return local?.let { domainMapper.mapRemote(it) }
    }

    override suspend fun refresh() {
        operationGetAll.refresh()
    }
}