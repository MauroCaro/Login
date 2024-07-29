package com.app.data.base.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

interface RepositoryOperation<ResultType, RequestType> {

    fun asFlow(): Flow<ResultType> = flow {
        val initData = getInitFromDb()
        if (shouldFetch(initData)) {
            if (isValidInitData(initData)) {
                initData?.let { emit(it) }
            }
            refresh()
        }
        subscribeDbUpdates().collect {
            emit(it)
        }
    }.distinctUntilChanged()

    suspend fun refresh() {
        try {
            val remoteData = createCall()
            saveResult(mapCallResult(remoteData))
        } catch (e: Exception) {
           // if (NetworkMonitor.getInstance().isConnected()) {
           //     throw e
                // If it is connected we need to spread the error.
                // If it is not connected the request should not break the flow
         //   }
        }
    }

    @WorkerThread
    fun subscribeDbUpdates(): Flow<ResultType>

    @WorkerThread
    fun shouldFetch(result: ResultType?): Boolean

    @WorkerThread
    suspend fun createCall(): RequestType

    @WorkerThread
    fun mapCallResult(result: RequestType): ResultType

    @WorkerThread
    suspend fun saveResult(result: ResultType)

    @WorkerThread
    suspend fun getInitFromDb(): ResultType? {
        return subscribeDbUpdates().firstOrNull()
    }

    fun isValidInitData(initData: ResultType?): Boolean {
        return initData != null && (initData as? List<*>)?.isNotEmpty() ?: true
    }
}