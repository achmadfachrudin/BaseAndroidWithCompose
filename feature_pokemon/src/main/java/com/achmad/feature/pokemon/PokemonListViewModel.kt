package com.achmad.feature.pokemon

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.achmad.baseandroid.service.PokemonPagingSource
import com.achmad.baseandroid.service.PokemonRepository
import com.achmad.feature.pokemon.data.model.PokemonItem
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScoped
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    fun getPokemonPagination(): Flow<PagingData<PokemonItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PokemonPagingSource(pokemonRepository)
        }.flow
    }
}
