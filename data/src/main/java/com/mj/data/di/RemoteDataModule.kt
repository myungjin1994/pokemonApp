package com.mj.data.di

import com.mj.data.api.PokemonDetailApiService
import com.mj.data.api.PokemonSearchApiService
import com.mj.data.repository.remote.PokemonRemoteDataSource
import com.mj.data.repository.remote.PokemonRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    @Singleton
    fun providePokemonSearchApiService(
        pokemonSearchApiService: PokemonSearchApiService,
        pokemonDetailApiService: PokemonDetailApiService
    ): PokemonRemoteDataSource {
        return PokemonRemoteDataSourceImpl(pokemonSearchApiService, pokemonDetailApiService)
    }
}