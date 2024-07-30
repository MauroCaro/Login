package com.app.login.base.ui.di

import com.app.login.base.navigation.Navigator
import com.app.login.base.navigation.NavigatorImpl
import com.app.login.base.ui.resource.ResourceManager
import com.app.login.base.ui.resource.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MainModule {

    @Singleton
    @Binds
    fun bindAppNavigator(navigatorImpl: NavigatorImpl): Navigator

    @Binds
    @Singleton
    fun bindResourceManager(resourceManagerImpl: ResourceManagerImpl): ResourceManager
}