package com.mj.pokemonapp.data.model.search

import com.google.gson.annotations.SerializedName

data class PokemonNameResponse(
    @SerializedName("pokemons") val pokemonNames: List<PokemonNameEntity>
)