package com.app.data.login

import com.app.data.base.repository.BaseRepositoryImpl
import com.app.data.common.user.local.UserLocalSource
import com.app.data.common.user.mapper.UserDataMapper
import com.app.data.common.user.mapper.UserDomainMapper
import com.app.data.common.user.model.UserEntity
import com.app.data.common.user.model.UserPojo
import com.app.data.login.remote.LoginRemoteSource
import com.app.domain.login.LoginRepository
import com.app.domain.login.model.UserInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localSource: UserLocalSource,
    private val remoteSource: LoginRemoteSource,
    private val loginDataMapper: UserDataMapper,
    private val loginDomainMapper: UserDomainMapper,
) : LoginRepository, BaseRepositoryImpl<UserInformation, UserEntity, UserPojo>(
    localSource,
    loginDataMapper,
    loginDomainMapper
) {

    /**
     * Here will normally go to remote and I will map the information in case it comes
     * remoteSource.checkLoginCredentials(userName, password)
     */
    override suspend fun checkLoginCredentials(
        userName: String,
        password: String
    ): Flow<UserInformation?> {
        return localSource.checkLoginCredentials(userName, password).let { userEntityFlow ->
            userEntityFlow.map { user ->
                user?.let { loginDomainMapper.mapRemote(it) }
            }
        }
    }
}

