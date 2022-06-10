package com.mj.data.model.search

import com.google.gson.annotations.SerializedName
import com.mj.data.model.search.PokemonNameEntity

data class PokemonNameResponse(
    @SerializedName("pokemons") val pokemonNames: List<PokemonNameEntity>
)