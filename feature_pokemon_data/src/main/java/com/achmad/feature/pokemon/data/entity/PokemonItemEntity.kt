package com.achmad.feature.pokemon.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonItemEntity(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)
