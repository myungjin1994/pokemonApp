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

            // 검색어가 포함된 포켓몬 리스트 필터
            val searchResult = result.getValue().filter {
                it.nameKorean.contains(searchString) || it.nameEnglish.contains(searchString, ignoreCase = true)
            }

            // 검색한 언어를 해당 포켓몬의 주이름(검색이름)으로 설정
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