package com.mj.pokemonapp.data.repository

import com.mj.pokemonapp.data.mapper.mapperToPokemonLocation
import com.mj.pokemonapp.data.mapper.mapperToPokemonName
import com.mj.pokemonapp.data.repository.remote.PokemonRemoteDataSource
import com.mj.pokemonapp.domain.model.PokemonLocation
import com.mj.pokemonapp.domain.model.PokemonName
import com.mj.pokemonapp.domain.repository.PokemonRepository
import com.mj.pokemonapp.util.Result
import com.mj.pokemonapp.util.provider.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : PokemonRepository {

    override suspend fun getPokemonList(): Result<List<PokemonName>> = withContext(dispatcherProvider.io) {
        return@withContext try {
            val result = pokemonRemoteDataSource.getPokemonList().body()?.pokemonNames ?: listOf()
            Result.Success(mapperToPokemonName(result))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPokemonLocations(): Result<List<PokemonLocation>> = withContext(dispatcherProvider.io) {
        return@withContext try {
            val result = pokemonRemoteDataSource.getPokemonLocations().body()?.pokemonLocations ?: listOf()
            Result.Success(mapperToPokemonLocation(result))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}