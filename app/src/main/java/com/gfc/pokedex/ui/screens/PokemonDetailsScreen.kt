package com.gfc.pokedex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gfc.pokedex.R
import com.gfc.pokedex.domain.model.Pokemon
import com.gfc.pokedex.ui.states.PokemonDetailsState

@Composable
fun PokemonDetailsScreen(
    modifier: Modifier = Modifier,
    state: PokemonDetailsState,
) = Column(
    modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    if (state.isLoading) {
        Text("Loading...")
    } else {
        with(state.pokemon!!) {
            Text(text = stringResource(R.string.pokemon_name, name))
            Text(text = stringResource(R.string.number, id))

            if (weight != null) {
                Text(text = stringResource(R.string.pokemon_weight, weight))
            }

            if (height != null) {
                Text(text = stringResource(R.string.pokemon_height, height))
            }

            if (baseExperience != null) {
                Text(text = stringResource(R.string.pokemon_base_experience, baseExperience))
            }

            if (specie != null) {
                Text(text = stringResource(R.string.pokemon_species, specie))
            }
        }
    }
}

@Composable
@Preview
fun PokemonDetailsScreenPreview() = PokemonDetailsScreen(
    state = PokemonDetailsState(
        pokemon = Pokemon(
            id = 25,
            name = "pikachu",
            weight = 60,
            height = 4,
            baseExperience = 112,
            specie = "Mouse Pokémon",
        ),
        isLoading = false,
    )
)
