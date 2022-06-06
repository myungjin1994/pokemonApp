package com.mj.pokemonapp.presentation.detail

import com.mj.pokemonapp.domain.model.PokemonDetail

sealed class DetailState {

    object Uninitialized : DetailState()

    object LoadingRead : DetailState()

    data class SuccessReadPokemonDetail(
        val pokemonDetail: PokemonDetail
    ) : DetailState()

    data class Error(
        val message: String
    ) : DetailState()


}
