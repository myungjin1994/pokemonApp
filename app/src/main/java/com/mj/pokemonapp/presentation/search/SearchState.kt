package com.mj.pokemonapp.presentation.search

import com.mj.pokemonapp.domain.model.PokemonName

sealed class SearchState {

    object Uninitialized : SearchState()

    object LoadingRead : SearchState()

    data class SuccessReadPokemonList(
        val pokemonList: List<PokemonName>
    ) : SearchState()

    data class Error(
        val message: String
    ) : SearchState()


}
