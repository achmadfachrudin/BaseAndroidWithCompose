package com.achmad.feature.pokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.achmad.baseandroid.theme.BaseComposeTheme
import com.achmad.feature.pokemon.data.model.PokemonItem
import com.google.android.play.core.splitcompat.SplitCompat

class PokemonDetailActivity : ComponentActivity() {

    companion object {
        private const val BUNDLE_KEY_POKEMON = "BUNDLE_KEY_POKEMON"

        fun createIntent(
            context: Context,
            pokemon: PokemonItem,
        ): Intent {
            return Intent(context, PokemonDetailActivity::class.java).apply {
                putExtra(BUNDLE_KEY_POKEMON, pokemon)
            }
        }
    }

    private val pokemon by lazy {
        intent.getParcelableExtra<PokemonItem>(BUNDLE_KEY_POKEMON)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BaseComposeTheme {
                PokemonDetailPageCompose(pokemon)
            }
        }
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        SplitCompat.installActivity(context)
    }
}
