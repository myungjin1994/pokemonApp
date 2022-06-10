package com.mj.data.mapper

import com.mj.data.model.detail.PokemonDetailEntity
import com.mj.data.model.search.PokemonLocationEntity
import com.mj.data.model.search.PokemonNameEntity
import com.mj.domain.model.PokemonDetail
import com.mj.domain.model.PokemonLocation
import com.mj.domain.model.PokemonName

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
fun PokemonDetailEntity.toPokemonDetail(): PokemonDetail {
    var pokemonImage: String? = null

    when {
        sprites?.frontDefault != null -> pokemonImage = sprites.frontDefault
        sprites?.backDefault != null -> pokemonImage = sprites.backDefault
        sprites?.backFemale != null -> pokemonImage = sprites.backFemale
        sprites?.backShiny != null -> pokemonImage = sprites.backShiny
        sprites?.backShinyFemale != null -> pokemonImage = sprites.backShinyFemale
        sprites?.frontFemale != null -> pokemonImage = sprites.frontFemale
        sprites?.frontShiny != null -> pokemonImage = sprites.frontShiny
        sprites?.frontShinyFemale != null -> pokemonImage = sprites.frontShinyFemale
    }

    return PokemonDetail(
        id = id,
        height = height ?: 0,
        name = name ?: "unknown",
        weight = weight ?: 0,
        image = pokemonImage
    )
}