package com.mj.pokemonapp.data.model.search


import com.google.gson.annotations.SerializedName

data class PokemonLocationResponse(
    @SerializedName("pokemons") val pokemonLocations: List<PokemonLocationEntity>
)