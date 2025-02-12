package com.gfc.pokedex.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gfc.pokedex.R
import com.gfc.pokedex.domain.model.Pokemon
import com.gfc.pokedex.ui.composables.PokemonListItem
import com.gfc.pokedex.ui.states.PokemonListState

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: PokemonListState,
    onSearchTermChanged: (String) -> Unit,
) {
    Column (modifier = modifier.fillMaxWidth()) {
        TextField (
            modifier = Modifier.fillMaxWidth(),
            value = state.searchTerm,
            onValueChange = onSearchTermChanged,
            label = { Text(stringResource(R.string.search_pokemon)) }
        )
        LazyColumn {
            state.pokemons.forEach { pokemon ->
                item {
                    PokemonListItem(pokemon = pokemon)
                }
            }
        }
    }
}

@Composable
@Preview
fun PokemonListScreenPreview() = PokemonListScreen(
    navController = rememberNavController(),
    state = PokemonListState(
        searchTerm = "Pokemon",
        pokemons = listOf(Pokemon(id = 1, name = "bulbasaur"))
    )
) {}