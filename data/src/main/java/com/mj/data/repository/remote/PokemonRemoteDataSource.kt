package com.mj.data.repository.remote

import com.mj.data.model.detail.PokemonDetailEntity
import com.mj.data.model.search.PokemonLocationResponse
import com.mj.data.model.search.PokemonNameResponse
import retrofit2.Response

interface PokemonRemoteDataSource {

    suspend fun getPokemonList(): Response<PokemonNameResponse>

    suspend fun getPokemonLocations(): Response<PokemonLocationResponse>

    suspend fun getPokemonDetail(id: String): Response<PokemonDetailEntity>

}