package com.mj.domain.usecase

import com.mj.domain.model.PokemonDetail
import com.mj.domain.repository.PokemonRepository
import com.mj.domain.utils.ResultOf
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): ResultOf<PokemonDetail> {
        return pokemonRepository.getPokemonDetail(id.toString())
    }
}