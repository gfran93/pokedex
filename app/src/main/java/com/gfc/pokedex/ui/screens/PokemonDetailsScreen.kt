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
import androidx.compose.ui.unit.dp
import com.gfc.pokedex.ui.composables.PokemonDetailsContent
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
    when {
        state.isLoading -> Text("Loading...")
        state.pokemon == null -> Text("There's been an error.")
        else -> PokemonDetailsContent(state.pokemon)
    }
}
