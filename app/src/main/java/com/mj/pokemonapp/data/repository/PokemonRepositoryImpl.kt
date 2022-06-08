package com.mj.pokemonapp.data.repository

import com.mj.pokemonapp.data.mapper.mapperToPokemonLocation
import com.mj.pokemonapp.data.mapper.mapperToPokemonName
import com.mj.pokemonapp.data.mapper.toPokemonDetail
import com.mj.pokemonapp.data.repository.remote.PokemonRemoteDataSource
import com.mj.pokemonapp.domain.model.PokemonDetail
import com.mj.pokemonapp.domain.model.PokemonLocation
import com.mj.pokemonapp.domain.model.PokemonName
import com.mj.pokemonapp.domain.repository.PokemonRepository
import com.mj.pokemonapp.util.Result
import com.mj.pokemonapp.util.dispatcherprovider.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : PokemonRepository {

    override suspend fun getPokemonList(): Result<List<PokemonName>> = withContext(dispatcherProvider.io) {
        return@withContext try {
            val result = pokemonRemoteDataSource.getPokemonList()

            if (result.isSuccessful) {
                Result.Success(mapperToPokemonName(result.body()?.pokemonNames ?: listOf()))
            } else {
                Result.Error(Exception(result.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPokemonLocations(): Result<List<PokemonLocation>> = withContext(dispatcherProvider.io) {
        return@withContext try {
            val result = pokemonRemoteDataSource.getPokemonLocations()

            if (result.isSuccessful) {
                Result.Success(mapperToPokemonLocation(result.body()?.pokemonLocations ?: listOf()))
            } else {
                Result.Error(Exception(result.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPokemonDetail(id: String): Result<PokemonDetail> = withContext(dispatcherProvider.io) {
        return@withContext try {
            val result = pokemonRemoteDataSource.getPokemonDetail(id)

            if (result.isSuccessful) {
                Result.Success(result.body()!!.toPokemonDetail())
            } else {
                Result.Error(Exception(result.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}