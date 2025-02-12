package com.gfc.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gfc.pokedex.ui.Screens
import com.gfc.pokedex.ui.screens.PokemonDetailsScreen
import com.gfc.pokedex.ui.screens.PokemonListScreen
import com.gfc.pokedex.ui.viewmodels.PokemonListViewModel

@Composable
fun NavigationStack(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.PokemonList.route
    ) {
        composable(Screens.PokemonList.route) {
            val viewModel: PokemonListViewModel = hiltViewModel()
            PokemonListScreen(
                navController = navController,
                state = viewModel.state.collectAsState().value,
                onSearchTermChanged = { viewModel.searchTermUpdated(it) }
            )
        }
        composable(Screens.PokemonDetails.route) {
            PokemonDetailsScreen()
        }
    }
}
