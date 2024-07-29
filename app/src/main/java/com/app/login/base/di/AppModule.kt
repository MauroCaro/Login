package com.app.login.base.di

import android.app.Application
import android.content.Context
import com.app.login.base.ui.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext
                           app: Context
    ): App {
        return app as App // Casteo seguro, ya que app es una instancia de Application
    }
}