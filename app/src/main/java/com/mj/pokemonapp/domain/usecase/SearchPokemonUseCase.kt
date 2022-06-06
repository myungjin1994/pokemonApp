package com.mj.pokemonapp.domain.usecase

import com.mj.pokemonapp.domain.model.PokemonName
import com.mj.pokemonapp.domain.repository.PokemonRepository
import com.mj.pokemonapp.util.Result
import com.mj.pokemonapp.util.getValue
import com.mj.pokemonapp.util.succeeded
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(searchString: String): Result<List<PokemonName>> {
        val result = pokemonRepository.getPokemonList()

        return if (result.succeeded) {
            val searchResult = result.getValue().filter {
                it.nameKorean.contains(searchString) || it.nameEnglish.contains(searchString, ignoreCase = true)
            }

            searchResult.forEach {
                if (it.nameKorean.contains(searchString)) {
                    it.searchName = it.nameKorean
                } else {
                    it.searchName = it.nameEnglish
                }
            }

            Result.Success(searchResult)
        } else {
            result
        }
    }
}