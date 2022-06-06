package com.mj.pokemonapp.domain.model

data class PokemonDetail(
    val id: Int,
    val height: Int,
    val name: String,
    val image: String?,
    val weight: Int
)