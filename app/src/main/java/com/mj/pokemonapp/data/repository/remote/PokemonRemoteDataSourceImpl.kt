package com.mj.pokemonapp.data.repository.remote

import com.mj.pokemonapp.data.api.PokemonDetailApiService
import com.mj.pokemonapp.data.api.PokemonSearchApiService
import com.mj.pokemonapp.data.model.detail.PokemonDetailEntity
import com.mj.pokemonapp.data.model.search.PokemonLocationResponse
import com.mj.pokemonapp.data.model.search.PokemonNameResponse
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