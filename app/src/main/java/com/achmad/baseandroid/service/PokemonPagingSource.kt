package com.achmad.baseandroid.service

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.achmad.feature.pokemon.data.model.PokemonItem
import kotlinx.coroutines.flow.last

class PokemonPagingSource(
    private val repository: PokemonRepository
) : PagingSource<Int, PokemonItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItem> {
        return try {
            val page = params.key ?: 1
            var list = listOf<PokemonItem>()
            val apiResult = repository.fetchPokemonList(page).last().data
            apiResult?.let { list = it }
            Log.d("POKEMONDEBUG", "PagingSource list $list")

            LoadResult.Page(
                data = list,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )
        } catch (e: Exception) {
            Log.d("POKEMONDEBUG", "PagingSource LoadResult $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonItem>): Int? {
        return null
    }
}
