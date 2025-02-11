package com.gfc.pokedex.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gfc.pokedex.domain.model.Pokemon
import com.gfc.pokedex.ui.composables.PokemonListItem
import com.gfc.pokedex.ui.states.PokemonListState

@Composable
fun PokemonListScreen(
    navController: NavController,
    state: PokemonListState,
) {
    Column {
        TextField(
            value = state.searchQuery,
            onValueChange = { /* todo: */ },
            label = { Text("Buscar Pokemon") }
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
        searchQuery = "Pokemon",
        pokemons = listOf(Pokemon(id = 1, name = "bulbasaur"))
    )
)