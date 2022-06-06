package com.mj.pokemonapp.data.repository

import com.mj.pokemonapp.data.model.search.PokemonLocationEntity
import com.mj.pokemonapp.data.model.search.PokemonNameEntity
import com.mj.pokemonapp.data.repository.remote.PokemonRemoteDataSource
import com.mj.pokemonapp.domain.repository.PokemonRepository
import com.mj.pokemonapp.util.Result
import com.mj.pokemonapp.util.provider.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : PokemonRepository {

    override suspend fun getPokemonList(): Result<List<PokemonNameEntity>> = withContext(dispatcherProvider.io) {
        return@withContext try {
            val result = pokemonRemoteDataSource.getPokemonList().body()?.pokemonNames ?: listOf()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPokemonLocations(): Result<List<PokemonLocationEntity>> = withContext(dispatcherProvider.io) {
        return@withContext try {
            val result = pokemonRemoteDataSource.getPokemonLocations().body()?.pokemonLocations ?: listOf()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}