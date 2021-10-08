package com.achmad.baseandroid.main

import android.content.SharedPreferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.achmad.baseandroid.service.PokemonPagingSource
import com.achmad.baseandroid.service.PokemonRepository
import com.achmad.feature.pokemon.data.model.PokemonItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val sharedPreferences: SharedPreferences,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val coba = sharedPreferences.getString("firstStoredString", "0")

    fun getPokemonPagination(): Flow<PagingData<PokemonItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PokemonPagingSource(pokemonRepository)
        }.flow
    }
}
