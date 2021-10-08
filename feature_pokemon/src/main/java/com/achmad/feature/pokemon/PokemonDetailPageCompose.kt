package com.achmad.feature.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.achmad.baseandroid.theme.component.BaseToolbar
import com.achmad.feature.pokemon.data.model.PokemonItem

@Composable
fun PokemonDetailPageCompose(
    model: PokemonItem,
) {
    Column {
        BaseToolbar(
            title = "Pokemon Detail",
            showLeftButton = false
        )
        Image(
            painter = rememberImagePainter(data = model.image),
            contentDescription = null,
            modifier = Modifier.size(360.dp)
        )
        Text(text = model.name)
    }
}
