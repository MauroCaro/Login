package com.app.data.common.user.local

import com.app.data.base.database.UpdateStorageOperation
import com.app.data.common.user.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserLocalSourceImpl @Inject constructor(
    private val dao: UserDao
) : UserLocalSource {

    override fun getAll(): Flow<List<UserEntity>> {
        return dao.getAll().flowOn(Dispatchers.IO)
    }

    override fun getById(id: String): Flow<UserEntity?> {
        return dao.getByIdFlow(id).flowOn(Dispatchers.IO)
    }

    override fun createOrUpdate(items: List<UserEntity>): List<UserEntity> {
        return object : UpdateStorageOperation<UserEntity>() {
            override fun getAllIds(): List<String>? = dao.getAllId()

            override fun createOrUpdate(item: UserEntity) = dao.insert(items)

            override fun delete(id: String) {
                dao.delete(id)
            }
        }.execute(items)
    }

    override fun createOrUpdate(items: UserEntity): UserEntity {
        if (dao.update(items) == 0) {
            dao.insert(items)
        }
        return items
    }

    override fun deleteAll(): Int {
        return dao.deleteAll()
    }

    override fun deleteById(id: String): Int = dao.delete(id)


    override fun loadById(id: String): UserEntity? {
        return dao.loadById(id)
    }

    override fun checkLoginCredentials(userName: String, password: String): UserEntity? {
        return dao.checkLoginCredentials(userName, password)
    }
}