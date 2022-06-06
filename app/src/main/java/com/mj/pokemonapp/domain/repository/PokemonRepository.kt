package com.mj.pokemonapp.domain.repository

import com.mj.pokemonapp.domain.model.PokemonLocation
import com.mj.pokemonapp.domain.model.PokemonName
import com.mj.pokemonapp.util.Result

interface PokemonRepository {

    suspend fun getPokemonList(): Result<List<PokemonName>>

    suspend fun getPokemonLocations(): Result<List<PokemonLocation>>

}