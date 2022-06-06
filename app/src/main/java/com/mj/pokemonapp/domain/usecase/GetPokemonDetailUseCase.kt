package com.mj.pokemonapp.domain.usecase

import com.mj.pokemonapp.domain.model.PokemonDetail
import com.mj.pokemonapp.domain.repository.PokemonRepository
import com.mj.pokemonapp.util.Result
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Result<PokemonDetail> {
        return pokemonRepository.getPokemonDetail(id.toString())
    }
}