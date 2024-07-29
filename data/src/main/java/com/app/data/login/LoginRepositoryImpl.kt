package com.app.data.login

import com.app.data.common.user.local.UserLocalSource
import com.app.data.common.user.mapper.UserDataMapper
import com.app.data.common.user.mapper.UserDomainMapper
import com.app.data.login.remote.LoginRemoteSource
import com.app.domain.login.LoginRepository
import com.app.domain.login.model.UserInformation
import javax.inject.Inject

 class LoginRepositoryImpl @Inject constructor(
    private val localSource: UserLocalSource,
    private val remoteSource: LoginRemoteSource,
    private val loginDataMapper: UserDataMapper,
    private val loginDomainMapper: UserDomainMapper,
) : LoginRepository {
    override suspend fun checkLoginCredentials(
        userName: String,
        password: String
    ): UserInformation? {
        /**
         * Here will normally go to remote and I will map the information in case it comes
         * remoteSource.checkLoginCredentials(userName, password)
         */
        return localSource.checkLoginCredentials(userName, password)?.let {
            loginDomainMapper.mapRemote(it)
        }
    }
}

