package com.mj.pokemonapp.data.repository.remote

import com.mj.pokemonapp.domain.model.PokemonDetail
import com.mj.pokemonapp.domain.model.PokemonLocation
import com.mj.pokemonapp.domain.model.PokemonName
import com.mj.pokemonapp.domain.repository.PokemonRepository
import com.mj.pokemonapp.util.Result

class FakePokemonRemoteDataSourceImpl() : PokemonRepository {

    override suspend fun getPokemonList(): Result<List<PokemonName>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonLocations(): Result<List<PokemonLocation>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonDetail(id: String): Result<PokemonDetail> {
        TODO("Not yet implemented")
    }
}