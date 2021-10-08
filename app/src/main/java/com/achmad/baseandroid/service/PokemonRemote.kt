package com.achmad.baseandroid.service

import com.achmad.feature.pokemon.data.entity.PokemonListEntity
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class PokemonRemote @Inject constructor(
    private val service: PokemonService
) {

    suspend fun fetchPokemonList(
        page: Int
    ): ApiResponse<PokemonListEntity> =
        service.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

    companion object {
        private const val PAGING_SIZE = 20
    }
}
