package com.achmad.baseandroid.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.achmad.feature.pokemon.data.model.PokemonItem

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonItem>)

    @Query("SELECT * FROM PokemonItem WHERE page = :page_")
    suspend fun getPokemonList(page_: Int): List<PokemonItem>

    @Query("SELECT * FROM PokemonItem WHERE page <= :page_")
    suspend fun getAllPokemonList(page_: Int): List<PokemonItem>
}
