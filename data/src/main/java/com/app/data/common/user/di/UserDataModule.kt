package com.app.data.common.user.di

import com.app.data.common.user.local.UserDao
import com.app.data.common.user.local.UserLocalSource
import com.app.data.common.user.local.UserLocalSourceImpl
import com.app.data.common.user.mapper.UserDataMapper
import com.app.data.common.user.mapper.UserDomainMapper
import com.app.data.login.LoginRepositoryImpl
import com.app.data.login.remote.LoginRemoteSource
import com.app.domain.login.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideLoginRepository(
        localSource: UserLocalSource,
        domainMapper: UserDomainMapper,
        dataMapper: UserDataMapper,
        remoteSource: LoginRemoteSource
    ): LoginRepository = LoginRepositoryImpl(
        localSource, remoteSource, dataMapper, domainMapper
    )
}


@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {

    @Provides
    @Singleton
    fun provideUserLocalSource(
        dao: UserDao
    ): UserLocalSource {
        return UserLocalSourceImpl(dao)
    }

}

