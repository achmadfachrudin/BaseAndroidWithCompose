package com.achmad.baseandroid.di

import com.achmad.baseandroid.cache.PokemonDao
import com.achmad.baseandroid.service.PokemonRemote
import com.achmad.baseandroid.service.PokemonRepository
import com.achmad.baseandroid.service.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRemote(pokedexService: PokemonService): PokemonRemote {
        return PokemonRemote(pokedexService)
    }

    @Provides
    fun providePokemonRepository(
        pokemonDao: PokemonDao,
        pokemonRemote: PokemonRemote
    ): PokemonRepository {
        return PokemonRepository(pokemonDao, pokemonRemote)
    }
}
