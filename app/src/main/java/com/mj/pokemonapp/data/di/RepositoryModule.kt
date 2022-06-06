package com.mj.pokemonapp.data.di

import com.mj.pokemonapp.data.repository.PokemonRepositoryImpl
import com.mj.pokemonapp.data.repository.remote.PokemonRemoteDataSource
import com.mj.pokemonapp.domain.repository.PokemonRepository
import com.mj.pokemonapp.util.provider.DispatcherProvider
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