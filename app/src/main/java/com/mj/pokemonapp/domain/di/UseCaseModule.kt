package com.mj.pokemonapp.domain.di

import com.mj.pokemonapp.domain.repository.PokemonRepository
import com.mj.pokemonapp.domain.usecase.GetPokemonDetailUseCase
import com.mj.pokemonapp.domain.usecase.GetPokemonLocationsUseCase
import com.mj.pokemonapp.domain.usecase.SearchPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPokemonDetailUseCase(pokemonRepository: PokemonRepository): GetPokemonDetailUseCase =
        GetPokemonDetailUseCase(pokemonRepository)

    @Provides
    @Singleton
    fun provideGetPokemonLocationsUseCase(pokemonRepository: PokemonRepository): GetPokemonLocationsUseCase =
        GetPokemonLocationsUseCase(pokemonRepository)

    @Provides
    @Singleton
    fun provideSearchPokemonUseCase(pokemonRepository: PokemonRepository): SearchPokemonUseCase =
        SearchPokemonUseCase(pokemonRepository)

}