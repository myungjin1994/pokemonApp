package com.mj.domain.usecase

import com.mj.domain.model.PokemonLocation
import com.mj.domain.repository.PokemonRepository
import javax.inject.Inject
import com.mj.domain.utils.ResultOf
import com.mj.domain.utils.getValue
import com.mj.domain.utils.succeeded

class GetPokemonLocationsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): ResultOf<List<PokemonLocation>> {
        val result = pokemonRepository.getPokemonLocations()

        return if (result.succeeded) {

            //일치하는 id의 서식지 리스트 필터
            val searchResult = result.getValue().filter {
                it.id == id
            }

            ResultOf.Success(searchResult)
        } else {
            result
        }
    }
}