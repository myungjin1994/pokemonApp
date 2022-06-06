package com.mj.pokemonapp.data.api

import com.mj.pokemonapp.data.model.search.PokemonLocationResponse
import com.mj.pokemonapp.data.model.search.PokemonNameResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonSearchApiService {

    @GET("pokemon_name")
    suspend fun getPokemonNameList(): Response<PokemonNameResponse>

    @GET("pokemon_locations")
    suspend fun getPokemonLocationList(): Response<PokemonLocationResponse>

}