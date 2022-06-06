package com.mj.pokemonapp.data.repository.remote

import com.mj.pokemonapp.data.model.detail.PokemonDetailEntity
import com.mj.pokemonapp.data.model.search.PokemonLocationResponse
import com.mj.pokemonapp.data.model.search.PokemonNameResponse
import retrofit2.Response

interface PokemonRemoteDataSource {

    suspend fun getPokemonList(): Response<PokemonNameResponse>

    suspend fun getPokemonLocations(): Response<PokemonLocationResponse>

    suspend fun getPokemonDetail(id: String): Response<PokemonDetailEntity>

}