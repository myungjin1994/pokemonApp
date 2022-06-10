package com.mj.data.model.search


import com.google.gson.annotations.SerializedName
import com.mj.data.model.search.PokemonLocationEntity

data class PokemonLocationResponse(
    @SerializedName("pokemons") val pokemonLocations: List<PokemonLocationEntity>
)