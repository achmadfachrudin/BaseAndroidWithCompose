package com.achmad.baseandroid.service

import android.util.Log
import androidx.annotation.WorkerThread
import com.achmad.baseandroid.cache.PokemonDao
import com.achmad.common.ApiResult
import com.achmad.feature.pokemon.data.mapper.toPokemonItem
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val cache: PokemonDao,
    private val remote: PokemonRemote
) {

    @WorkerThread
    fun fetchPokemonList(
        page: Int,
    ) = flow {
        emit(ApiResult.Loading)

        var pokemons = cache.getPokemonList(page)
        if (pokemons.isEmpty()) {
            val response = remote.fetchPokemonList(page = page)
            Log.d("POKEMONDEBUG", "response $response")

            response.suspendOnSuccess {
                Log.d("POKEMONDEBUG", "suspendOnSuccess ${data.results}")

                pokemons = data.results.map {
                    it.toPokemonItem(page)
                }
                cache.insertPokemonList(pokemons)
                emit(ApiResult.Success(cache.getAllPokemonList(page)))
            }.suspendOnError {
                Log.d("POKEMONDEBUG", "suspendOnError $this")
                // handles error cases
                emit(ApiResult.Error(this.toString()))
            }.suspendOnException {
                Log.d("POKEMONDEBUG", "suspendOnException $this")
                // handles exceptional cases
                emit(ApiResult.Error(this.toString()))
            }
        } else {
            emit(ApiResult.Success(cache.getAllPokemonList(page)))
        }
    }.flowOn(Dispatchers.IO)
}
