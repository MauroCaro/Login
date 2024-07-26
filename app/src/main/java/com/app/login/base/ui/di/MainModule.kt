package com.app.login.base.ui.di

import com.app.login.base.navigation.Navigator
import com.app.login.base.navigation.NavigatorImpl
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
}