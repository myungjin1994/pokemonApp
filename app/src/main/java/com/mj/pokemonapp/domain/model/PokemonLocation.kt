package com.mj.pokemonapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonLocation(
    val id: Int,
    val lat: Double,
    val lng: Double
) : Parcelable
