package com.mj.presentation.di

import android.content.Context
import com.mj.presentation.utils.resourceprovider.DefaultResourcesProvider
import com.mj.presentation.utils.resourceprovider.ResourcesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourceModule {

    @Singleton
    @Provides
    fun provideResourcesProvider(
        @ApplicationContext appContext: Context
    ): ResourcesProvider = DefaultResourcesProvider(appContext)
}