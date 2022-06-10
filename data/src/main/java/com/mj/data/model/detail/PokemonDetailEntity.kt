package com.mj.data.model.detail


import com.google.gson.annotations.SerializedName

data class PokemonDetailEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sprites")
    val sprites: Sprites?,
    @SerializedName("weight")
    val weight: Int?
) {

    data class Sprites(
        @SerializedName("front_default")
        val frontDefault: String?,
        @SerializedName("front_female")
        val frontFemale: String?,
        @SerializedName("front_shiny")
        val frontShiny: String?,
        @SerializedName("front_shiny_female")
        val frontShinyFemale: String?,
        @SerializedName("back_default")
        val backDefault: String?,
        @SerializedName("back_female")
        val backFemale: String?,
        @SerializedName("back_shiny")
        val backShiny: String?,
        @SerializedName("back_shiny_female")
        val backShinyFemale: String?
    )

}