package com.mj.pokemonapp.data.model.search


import com.google.gson.annotations.SerializedName

data class PokemonNameEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("names") val names: List<String>
)