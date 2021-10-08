package com.achmad.feature.pokemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.achmad.baseandroid.theme.BaseComposeTheme
import com.achmad.feature.pokemon.data.model.PokemonItem
import com.achmad.feature.pokemon.di.buildDaggerPokemonComponent
import com.google.android.play.core.splitcompat.SplitCompat
import javax.inject.Inject

class PokemonListActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: PokemonListViewModel

    private fun inject() {
        buildDaggerPokemonComponent(this.applicationContext).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)

        setContent {
            BaseComposeTheme {
                PokemonListPageCompose(
                    viewModel = viewModel,
                    onItemClick = { goToDetail(it) }
                )
            }
        }
    }

    private fun goToDetail(pokemon: PokemonItem) {
        startActivity(PokemonDetailActivity.createIntent(this, pokemon))
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        SplitCompat.installActivity(context)
    }
}
