package com.mj.pokemonapp.domain.model

data class PokemonName(
    val id: Int,
    var searchName: String = "",
    val nameKorean: String,
    val nameEnglish: String
)
