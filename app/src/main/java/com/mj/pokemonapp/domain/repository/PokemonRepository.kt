package com.mj.pokemonapp.domain.repository

import com.mj.pokemonapp.data.model.search.PokemonLocationEntity
import com.mj.pokemonapp.data.model.search.PokemonNameEntity
import com.mj.pokemonapp.util.Result

interface PokemonRepository {

    suspend fun getPokemonList(): Result<List<PokemonNameEntity>>

    suspend fun getPokemonLocations(): Result<List<PokemonLocationEntity>>

}