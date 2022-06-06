package com.mj.pokemonapp.data.di

import com.mj.pokemonapp.data.api.PokemonSearchApiService
import com.mj.pokemonapp.data.repository.remote.PokemonRemoteDataSource
import com.mj.pokemonapp.data.repository.remote.PokemonRemoteDataSourceImpl
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
    fun providePokemonSearchApiService(pokemonSearchApiService: PokemonSearchApiService): PokemonRemoteDataSource {
        return PokemonRemoteDataSourceImpl(pokemonSearchApiService)
    }
}