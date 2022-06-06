package com.mj.pokemonapp.data.api

import com.mj.pokemonapp.data.model.detail.PokemonDetailEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailApiService {

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: String
    ): Response<PokemonDetailEntity>


}