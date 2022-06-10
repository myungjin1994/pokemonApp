package com.mj.data.di

import com.mj.data.repository.PokemonRepositoryImpl
import com.mj.data.repository.remote.PokemonRemoteDataSource
import com.mj.data.utils.dispatcherprovider.DispatcherProvider
import com.mj.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonRemoteDataSource: PokemonRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ): PokemonRepository = PokemonRepositoryImpl(pokemonRemoteDataSource, dispatcherProvider)
}