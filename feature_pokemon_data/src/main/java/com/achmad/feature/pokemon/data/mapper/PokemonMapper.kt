package com.achmad.feature.pokemon.data.mapper

import com.achmad.feature.pokemon.data.entity.PokemonItemEntity
import com.achmad.feature.pokemon.data.model.PokemonItem

fun PokemonItemEntity.toPokemonItem(page: Int): PokemonItem {
    val index = url.split("/").dropLast(1).last()
    val image =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"

    return PokemonItem(
        page = page,
        name = name,
        url = url,
        image = image
    )
}
