package com.achmad.feature.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.achmad.baseandroid.theme.component.BaseToolbar
import com.achmad.feature.pokemon.data.model.PokemonItem

@Composable
fun PokemonListPageCompose(
    viewModel: PokemonListViewModel,
    onItemClick: (model: PokemonItem) -> Unit,
) {
    val lazyPokemonItems = viewModel.getPokemonPagination().collectAsLazyPagingItems()

    Column {
        BaseToolbar(
            title = "Pokemon",
            showLeftButton = false
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {
            items(lazyPokemonItems) { model ->
                model?.let {
                    RowPokemon(
                        model
                    ) { onItemClick(model) }
                }
            }
        }
        lazyPokemonItems.apply {
//            when {
//                loadState.refresh is LoadState.Loading -> {
//                    // load state when first response page is loading
//                    Text(text = "Lagi Loading")
//                }
//                loadState.append is LoadState.Loading -> {
//                    // load state when next response page is loading
//                    Text(text = "Lagi Loading")
//                }
//                loadState.append is LoadState.Error -> {
//                    Text(text = "Lagi Error")
//                }
//            }
        }
    }
}

@Composable
fun RowPokemon(
    model: PokemonItem,
    onItemClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = model.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Text(text = model.name)
    }
}
