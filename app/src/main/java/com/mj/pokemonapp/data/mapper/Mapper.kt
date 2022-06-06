package com.mj.pokemonapp.data.mapper

import com.mj.pokemonapp.data.model.search.PokemonNameEntity
import com.mj.pokemonapp.domain.model.PokemonName

//PokemonNameEntity -> PokemonName
fun mapperToPokemonName(PokemonNameEntities: List<PokemonNameEntity>): List<PokemonName> {
    return PokemonNameEntities.toList().map {
        it.toPokemonName()
    }
}

fun PokemonNameEntity.toPokemonName() = PokemonName(
    id = id,
    nameKorean = names[0],
    nameEnglish = names[1]
)
