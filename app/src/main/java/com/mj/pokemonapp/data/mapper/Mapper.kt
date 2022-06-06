package com.mj.pokemonapp.data.mapper

import com.mj.pokemonapp.data.model.detail.PokemonDetailEntity
import com.mj.pokemonapp.data.model.search.PokemonLocationEntity
import com.mj.pokemonapp.data.model.search.PokemonNameEntity
import com.mj.pokemonapp.domain.model.PokemonDetail
import com.mj.pokemonapp.domain.model.PokemonLocation
import com.mj.pokemonapp.domain.model.PokemonName

//PokemonNameEntity -> PokemonName
fun mapperToPokemonName(pokemonNameEntities: List<PokemonNameEntity>): List<PokemonName> {
    return pokemonNameEntities.toList().map {
        it.toPokemonName()
    }
}

fun PokemonNameEntity.toPokemonName() = PokemonName(
    id = id,
    nameKorean = names[0],
    nameEnglish = names[1]
)

//PokemonLocationEntity -> PokemonLocation
fun mapperToPokemonLocation(pokemonLocationEntities: List<PokemonLocationEntity>): List<PokemonLocation> {
    return pokemonLocationEntities.toList().map {
        it.toPokemonLocation()
    }
}

fun PokemonLocationEntity.toPokemonLocation() = PokemonLocation(
    id = id,
    lat = lat,
    lng = lng
)

//PokemonDetailEntity -> PokemonDetail
fun mapperToPokemonDetail(pokemonDetailEntities: List<PokemonDetailEntity>): List<PokemonDetail> {
    return pokemonDetailEntities.toList().map {
        it.toPokemonDetail()
    }
}

fun PokemonDetailEntity.toPokemonDetail(): PokemonDetail {
    var pokemonImage: String? = null
    if (sprites?.frontDefault != null) {
        pokemonImage = sprites.frontDefault
    } else if (sprites?.backDefault != null) {
        pokemonImage = sprites.backDefault
    } else if (sprites?.backFemale != null) {
        pokemonImage = sprites.backFemale
    } else if (sprites?.backShiny != null) {
        pokemonImage = sprites.backShiny
    } else if (sprites?.backShinyFemale != null) {
        pokemonImage = sprites.backShinyFemale
    } else if (sprites?.frontFemale != null) {
        pokemonImage = sprites.frontFemale
    } else if (sprites?.frontShiny != null) {
        pokemonImage = sprites.frontShiny
    } else if (sprites?.frontShinyFemale != null) {
        pokemonImage = sprites.frontShinyFemale
    }

    return PokemonDetail(
        id = id,
        height = height ?: 0,
        name = name ?: "unknown",
        weight = weight ?: 0,
        image = pokemonImage
    )
}