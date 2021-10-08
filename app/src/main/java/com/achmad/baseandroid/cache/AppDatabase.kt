package com.achmad.baseandroid.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.achmad.feature.pokemon.data.model.PokemonItem

@Database(entities = [PokemonItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}
