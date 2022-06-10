package com.mj.data.repository.remote

import com.mj.data.api.PokemonDetailApiService
import com.mj.data.api.PokemonSearchApiService
import com.mj.data.model.detail.PokemonDetailEntity
import com.mj.data.model.search.PokemonLocationResponse
import com.mj.data.model.search.PokemonNameResponse
import com.mj.data.repository.remote.PokemonRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokemonSearchApiService: PokemonSearchApiService,
    private val pokemonDetailApiService: PokemonDetailApiService
) : PokemonRemoteDataSource {

    override suspend fun getPokemonList(): Response<PokemonNameResponse> {
        return pokemonSearchApiService.getPokemonNameList()
    }

    override suspend fun getPokemonLocations(): Response<PokemonLocationResponse> {
        return pokemonSearchApiService.getPokemonLocationList()
    }

    override suspend fun getPokemonDetail(id: String): Response<PokemonDetailEntity> {
        return pokemonDetailApiService.getPokemonDetail(id)
    }


}