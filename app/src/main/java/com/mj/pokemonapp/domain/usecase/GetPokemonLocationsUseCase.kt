package com.mj.pokemonapp.domain.usecase

import com.mj.pokemonapp.domain.model.PokemonLocation
import com.mj.pokemonapp.domain.repository.PokemonRepository
import com.mj.pokemonapp.util.Result
import com.mj.pokemonapp.util.getValue
import com.mj.pokemonapp.util.succeeded
import javax.inject.Inject

class GetPokemonLocationsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Result<List<PokemonLocation>> {
        val result = pokemonRepository.getPokemonLocations()

        return if (result.succeeded) {

            //일치하는 id의 서식지 리스트 필터
            val searchResult = result.getValue().filter {
                it.id == id
            }

            Result.Success(searchResult)
        } else {
            result
        }
    }
}