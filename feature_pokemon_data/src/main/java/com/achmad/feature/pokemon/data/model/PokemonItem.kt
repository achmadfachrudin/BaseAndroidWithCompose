package com.achmad.feature.pokemon.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class PokemonItem(
    var page: Int = 0,
    @PrimaryKey val name: String,
    val url: String,
    val image: String
) : Parcelable
