package com.mj.domain.model


data class PokemonDetail(
    val id: Int,
    val height: Int,
    val name: String,
    val image: String?,
    val weight: Int
)