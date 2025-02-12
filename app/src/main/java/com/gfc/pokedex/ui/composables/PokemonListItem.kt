package com.gfc.pokedex.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gfc.pokedex.R
import com.gfc.pokedex.domain.model.Pokemon

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onItemClicked: (Pokemon) -> Unit,
) = Column(
    modifier = modifier
        .padding(all = 8.dp)
        .fillMaxWidth()
        .clickable { onItemClicked(pokemon) },
    horizontalAlignment = Alignment.CenterHorizontally
) {
    with(pokemon) {
        Text(text = stringResource(R.string.number, id))
        Text(text = name)
    }
}

@Composable
@Preview
fun PokemonListItemPreview() = PokemonListItem(
    pokemon = Pokemon(
        id = 1, name = "Bulbasaur",
        weight = null,
        height = null,
        baseExperience = null,
        specie = null,
        types = null,
        abilities = null,
        imageFileName = null,
    )
) {}