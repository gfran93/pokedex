package com.gfc.pokedex.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gfc.pokedex.domain.model.Pokemon

@Composable
fun PokemonListItem(pokemon: Pokemon) = Row {
    with(pokemon) {
        Text(text = id.toString())
        Text(text = name)
    }
}

@Composable
@Preview
fun PokemonListItemPreview() = PokemonListItem(pokemon = Pokemon(id = 1, name = "bulbasaur"))