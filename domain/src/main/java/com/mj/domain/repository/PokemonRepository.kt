package com.mj.domain.repository

import com.mj.domain.model.PokemonDetail
import com.mj.domain.model.PokemonLocation
import com.mj.domain.model.PokemonName
import com.mj.domain.utils.ResultOf

interface PokemonRepository {

    suspend fun getPokemonList(): ResultOf<List<PokemonName>>

    suspend fun getPokemonLocations(): ResultOf<List<PokemonLocation>>

    suspend fun getPokemonDetail(id: String): ResultOf<PokemonDetail>

}