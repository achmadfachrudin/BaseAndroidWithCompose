package com.achmad.baseandroid.di

import android.content.SharedPreferences
import com.achmad.baseandroid.cache.PokemonDao
import com.achmad.baseandroid.service.PokemonRemote
import com.achmad.baseandroid.service.PokemonRepository
import com.achmad.baseandroid.service.PokemonService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FeaturePokemonDependencies {

    fun sharedPreferences(): SharedPreferences
    fun pokemonRepository(): PokemonRepository
    fun pokemonService(): PokemonService
    fun pokemonRemote(): PokemonRemote
    fun pokemonDao(): PokemonDao
}
