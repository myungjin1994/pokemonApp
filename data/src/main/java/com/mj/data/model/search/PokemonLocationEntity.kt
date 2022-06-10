package com.mj.data.model.search


import com.google.gson.annotations.SerializedName

data class PokemonLocationEntity(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double,
    @SerializedName("id") val id: Int
)