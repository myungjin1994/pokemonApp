package com.mj.data.di

import com.mj.data.utils.dispatcherprovider.DefaultDispatcherProvider
import com.mj.data.utils.dispatcherprovider.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
}