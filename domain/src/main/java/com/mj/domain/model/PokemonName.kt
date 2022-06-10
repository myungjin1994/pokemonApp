package com.mj.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonName(
    val id: Int,
    var searchName: String = "",
    val nameKorean: String,
    val nameEnglish: String
) : Parcelable
